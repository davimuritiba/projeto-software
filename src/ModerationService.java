import java.util.*;

public class ModerationService {
    private Map<UUID, Set<UUID>> reportedPosts; // postId -> set of reporterIds
    private PostService postService;

    public ModerationService(PostService postService) {
        this.postService = postService;
        this.reportedPosts = new HashMap<>();
    }

    // Usuário reporta um post
    public boolean reportPost(UUID postId, UUID reporterId) {
        Post post = postService.getPostById(postId);
        if (post == null) return false;

        reportedPosts.computeIfAbsent(postId, k -> new HashSet<>());

        // Evita múltiplos reports do mesmo usuário
        if (reportedPosts.get(postId).contains(reporterId)) return false;

        reportedPosts.get(postId).add(reporterId);
        return true;
    }

    // Retorna os posts que foram reportados
    public List<Post> getReportedPosts() {
        List<Post> result = new ArrayList<>();
        for (UUID postId : reportedPosts.keySet()) {
            Post post = postService.getPostById(postId);
            if (post != null) {
                result.add(post);
            }
        }
        return result;
    }

    // Moderador remove o post
    public boolean removeReportedPost(UUID postId) {
        if (postService.deletePost(postId)) {
            reportedPosts.remove(postId);
            return true;
        }
        return false;
    }

    // Ver quantidade de reports
    public int getReportCount(UUID postId) {
        return reportedPosts.getOrDefault(postId, Collections.emptySet()).size();
    }
}

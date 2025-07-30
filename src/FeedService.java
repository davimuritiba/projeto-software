
import java.util.List;
import java.util.Set;
import java.util.UUID;
public class FeedService {

    private PostService postService;
    private FriendService friendService;
    private EventService eventService;

    public FeedService(PostService postService, FriendService friendService, EventService eventService) {
        this.postService = postService;
        this.friendService = friendService;
        this.eventService = eventService;
    }

    public void showFeed(UUID userId) {
        System.out.println("=== FEED DE ATIVIDADES ===");

        // Mostrar posts de amigos
        Set<UUID> friends = friendService.getFriends(userId);
        for (UUID friendId : friends) {
            List<Post> posts = postService.getPostsByUser(friendId);
            for (Post p : posts) {
                System.out.println("[Post de " + friendId + "]: " + p.getContent());
            }
        }

        // Mostrar eventos futuros
        List<Event> events = eventService.getEventsByUser(userId);
        for (Event e : events) {
            System.out.println("[Evento]: " + e.getTitle() + " em " + e.getDateTime());
        }
    }
}

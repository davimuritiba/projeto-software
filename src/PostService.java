import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
public class PostService
    {
        List <Post> posts;

        public PostService()
            {
                posts = new ArrayList<>();
            }

        public Post createPost(UUID userId, String content, String postType)
            {
                Post post = new Post(userId, content, postType);
                posts.add(post);
                return post;
            }
        public boolean editPost(UUID postId, String newContent, String newPostType)
            {
                for(Post post : posts)
                    {
                        if(post.getId().equals(postId))
                            {
                                post.setContent(newContent);
                                post.setType(newPostType);
                                return true;
                            }
                    }
                return false;
            }
        public boolean deletePost(UUID postId)
            {
                return posts.removeIf(post -> post.getId().equals(postId));
            }

        public List<Post> getPostsByUser(UUID userId)
            {
                List<Post> userPosts = new ArrayList<>();
                for (Post post : posts)
                    {
                        if (post.getUserId().equals(userId))
                            {
                                userPosts.add(post);
                            }
                    }
                return userPosts;
            }

        public List<Post> getAllPosts()
            {
                return new ArrayList<>(posts);
            }
    }


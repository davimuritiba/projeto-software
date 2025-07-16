import java.util.UUID;
import java.time.LocalDateTime;
public class Post
    {
        private UUID id;
        private UUID userId;
        private String content;
        private LocalDateTime createdAt;
        private String postType;


        public Post(UUID userId, String content, String postType)
            {
                this.id = UUID.randomUUID();
                this.userId = userId;
                this.content = content;
                this.createdAt =LocalDateTime.now();
                this.postType = postType;
            }
        public UUID getId()
            {
                return id;
            }
        public UUID getUserId()
            {
                return userId;
            }
        public String getContent()
            {
                return content;
            }

        public LocalDateTime getCreatedAt()
            {
                return createdAt;
            }
        public String getPostType()
            {
                return postType;
            }

        public void setContent(String content)
            {
                this.content = content;
            }
        public void setType(String postType)
            {
                this.postType = postType;
            }

    }

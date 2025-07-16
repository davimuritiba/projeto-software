import java.util.UUID;
import java.time.LocalDateTime;
public class Message
    {
        private UUID id;
        private UUID senderId;
        private UUID receiverId;
        private String content;
        private LocalDateTime sentDate;
        private boolean read;

        public Message(UUID senderId, UUID receiverId, String content)
            {
                this.id = UUID.randomUUID();
                this.senderId = senderId;
                this.receiverId = receiverId;
                this.content = content;
                this.sentDate = LocalDateTime.now();
                this.read = false;
            }

        public UUID getId()
            {
                return id;
            }

        public UUID getSenderId()
            {
                return senderId;
            }

        public UUID getReceiverId()
            {
                return receiverId;
            }

        public String getContent()
            {
                return content;
            }

        public LocalDateTime getSentDate()
            {
                return sentDate;
            }

        public boolean isRead()
            {
                return read;
            }

        public void markAsRead()
            {
                this.read = true;
            }
    }

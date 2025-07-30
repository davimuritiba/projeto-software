import java.util.UUID;
import java.time.LocalDateTime;

public class Notification
{
    private UUID id;
    private UUID userId;
    private String message;
    private LocalDateTime timestamp;
    private boolean read;

    public Notification(UUID userId, String message)
    {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.read = false;
    }

    public UUID getUserId() { return userId; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public boolean isRead() { return read; }

    public void markAsRead() {
        this.read = true;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + message + (read ? " (lida)" : " (não lida)");
    }
}

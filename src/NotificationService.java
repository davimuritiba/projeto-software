import java.util.*;

public class NotificationService {
    private Map<UUID, List<Notification>> notifications = new HashMap<>();

    public void sendNotification(UUID userId, String message) {
        notifications.computeIfAbsent(userId, k -> new ArrayList<>())
                .add(new Notification(userId, message));
    }

    public List<Notification> getNotifications(UUID userId) {
        return notifications.getOrDefault(userId, new ArrayList<>());
    }

    public void markAllAsRead(UUID userId) {
        for (Notification n : getNotifications(userId)) {
            n.markAsRead();
        }
    }
}

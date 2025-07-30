import java.util.UUID;

public class PrivacyService {

    private FriendService friendService;

    public PrivacyService(FriendService friendService) {
        this.friendService = friendService;
    }

    // Verifica se viewer pode ver os posts do owner
    public boolean canViewPost(User viewer, User owner) {
        PrivacyLevel level = owner.getPostPrivacy();

        return switch (level) {
            case PUBLIC -> true;
            case FRIENDS_ONLY -> friendService.isAlreadyFriends(viewer.getId(), owner.getId());
            case PRIVATE -> viewer.getId().equals(owner.getId());
        };
    }

    // Verifica se sender pode enviar mensagem para recipient
    public boolean canSendMessage(User sender, User recipient) {
        PrivacyLevel level = recipient.getMessagePrivacy();

        return switch (level) {
            case PUBLIC -> true;
            case FRIENDS_ONLY -> friendService.isAlreadyFriends(sender.getId(), recipient.getId());
            case PRIVATE -> sender.getId().equals(recipient.getId());
        };
    }

    // Verifica se viewer pode ver a lista de amigos do owner
    public boolean canViewFriendList(User viewer, User owner) {
        PrivacyLevel level = owner.getFriendListPrivacy();

        return switch (level) {
            case PUBLIC -> true;
            case FRIENDS_ONLY -> friendService.isAlreadyFriends(viewer.getId(), owner.getId());
            case PRIVATE -> viewer.getId().equals(owner.getId());
        };
    }
}

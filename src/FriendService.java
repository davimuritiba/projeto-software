import java.util.*;

public class FriendService
{
    private Map<UUID, Set<UUID>> friends;
    private List<FriendRequest> requests;

    public FriendService()
        {
            this.friends = new HashMap<>();
            this.requests = new ArrayList<>();
        }

    // Enviar pedido de amizade
    public boolean sendFriendRequest(UUID fromUserId, UUID toUserId)
    {
        // Impede pedido a si mesmo ou duplicado
        if (fromUserId.equals(toUserId) || isAlreadyFriends(fromUserId, toUserId))
            {
                return false;
            }

        // Verifica se já existe um pedido pendente
        for (FriendRequest r : requests)
            {
                if (r.getSenderId().equals(fromUserId) && r.getReceiverId().equals(toUserId) && r.getStatus() == RequestStatus.PENDING)
                    {
                        return false;
                    }
            }

        requests.add(new FriendRequest(fromUserId, toUserId));
        return true;
    }

    // Aceitar pedido
    public boolean acceptFriendRequest(UUID fromUserId, UUID toUserId)
    {
        for (FriendRequest r : requests) {
            if (r.getSenderId().equals(fromUserId) && r.getReceiverId().equals(toUserId) && r.getStatus() == RequestStatus.PENDING) {
                r.accept();
                addFriendship(fromUserId, toUserId);
                return true;
            }
        }
        return false;
    }

    private void addFriendship(UUID u1, UUID u2) {
        friends.computeIfAbsent(u1, k -> new HashSet<>()).add(u2);
        friends.computeIfAbsent(u2, k -> new HashSet<>()).add(u1);
    }

    public boolean isAlreadyFriends(UUID u1, UUID u2) {
        return friends.getOrDefault(u1, new HashSet<>()).contains(u2);
    }

    public Set<UUID> getFriends(UUID userId) {
        return friends.getOrDefault(userId, new HashSet<>());
    }

    public List<FriendRequest> getPendingRequests(UUID userId) {
        List<FriendRequest> result = new ArrayList<>();
        for (FriendRequest r : requests) {
            if (r.getReceiverId().equals(userId) && r.getStatus() == RequestStatus.PENDING) {
                result.add(r);
            }
        }
        return result;
    }
}

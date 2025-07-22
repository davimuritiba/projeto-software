import java.util.*;

public class FriendService
{
    private Map<UUID, Set<UUID>> friends; //hashmap que contem amizades ja estabelecidas
    private List<FriendRequest> requests; //lista de requisicoes de amizade

    public FriendService()
        {
            this.friends = new HashMap<>();
            this.requests = new ArrayList<>();
        }

    //envia pedido de amizade
    public boolean sendFriendRequest(UUID fromUserId, UUID toUserId)
    {
        //impeded pedido a si mesmo e pedido com amizades ja estabelecias
        if (fromUserId.equals(toUserId) || isAlreadyFriends(fromUserId, toUserId))
            {
                return false;
            }

        //verifica se ja ha um pedido pendente
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

    //aceita pedido
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

    private void addFriendship(UUID u1, UUID u2)
    {
        //cria um hashmap caso nao exista para registrar a nova amizade
        friends.computeIfAbsent(u1, k -> new HashSet<>()).add(u2);
        friends.computeIfAbsent(u2, k -> new HashSet<>()).add(u1);
    }

    public boolean isAlreadyFriends(UUID u1, UUID u2)
    {
        return friends.getOrDefault(u1, new HashSet<>()).contains(u2);
    }

    public Set<UUID> getFriends(UUID userId)
    {
        return friends.getOrDefault(userId, new HashSet<>());
    }

    public List<FriendRequest> getPendingRequests(UUID userId)
    {
        List<FriendRequest> result = new ArrayList<>();
        for (FriendRequest r : requests)
        {
            if (r.getReceiverId().equals(userId) && r.getStatus() == RequestStatus.PENDING) {
                result.add(r);
            }
        }
        return result;
    }
}

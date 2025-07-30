import java.util.UUID;

public class FriendRequest
    {
        private UUID senderId;
        private UUID receiverId;
        private RequestStatus status;

        public FriendRequest(UUID senderId, UUID receiverId)
            {
                this.senderId = senderId;
                this.receiverId = receiverId;
                this.status = RequestStatus.PENDING;
            }
        public UUID getSenderId()
            {
                return senderId;
            }

        public UUID getReceiverId()
            {
                return receiverId;
            }

        public RequestStatus getStatus()
            {
                return status;
            }

        public void accept()
            {
                this.status = RequestStatus.ACCEPTED;
            }

        public void reject()
            {
                this.status = RequestStatus.REJECTED;
            }

        @Override
        public String toString()
            {
                return "FriendRequest{" +
                        "sender=" + senderId +
                        ", receiver=" + receiverId +
                        ", status=" + status +
                        '}';
            }
    }

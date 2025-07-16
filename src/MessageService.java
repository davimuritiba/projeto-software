import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
public class MessageService
    {
        private List<Message> messages;

        public MessageService()
            {
                this.messages = new ArrayList<>();
            }

        public Message sendMessage(UUID senderId, UUID receiverId, String content)
            {
                Message message = new Message(senderId, receiverId, content);
                messages.add(message);
                return message;
            }

        public List<Message> getInbox(UUID userId)
            {
                List<Message> inbox = new ArrayList<>();
                for (Message msg : messages)
                    {
                        if (msg.getReceiverId().equals(userId))
                            {
                                inbox.add(msg);
                            }
                    }
                return inbox;
            }
            public List <Message> getSentMessages(UUID userId)
                {
                    List<Message> sent = new ArrayList<>();
                    for (Message msg : messages)
                        {
                            if (msg.getSenderId().equals(userId))
                                {
                                    sent.add(msg);
                                }
                        }
                    return sent;
                }
            public boolean markMessageAsRead(UUID messageId)
                {
                    for (Message msg : messages)
                        {
                            if (msg.getId().equals(messageId))
                                {
                                    msg.markAsRead();
                                    return true;
                                }
                        }
                    return false;
                }


    }

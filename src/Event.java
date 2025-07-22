import java.time.LocalDateTime;
import java.util.*;

public class Event
    {
        private UUID id;
        private String title;
        private String description;
        private LocalDateTime dateTime;
        private UUID creatorId;
        private Set<UUID> participants;

        public Event(String title, String description, LocalDateTime dateTime, UUID creatorId)
            {
                this.id = UUID.randomUUID();
                this.title = title;
                this.description = description;
                this.dateTime = dateTime;
                this.creatorId = creatorId;
                this.participants = new HashSet<>();
                participants.add(creatorId);
            }

        public UUID getId()
            {
                return id;
            }

        public String getTitle()
            {
                return title;
            }

        public LocalDateTime getDateTime()
            {
                return dateTime;
            }

        public UUID getCreatorId()
            {
                return creatorId;
            }

        public boolean addParticipant(UUID userId)
            {
                return participants.add(userId);
            }

        public Set<UUID> getParticipants()
            {
                return participants;
            }

        @Override
        public String toString()
            {
                return "Evento: " + title + " em " + dateTime + " com " + participants.size() + " participantes.";
            }
    }

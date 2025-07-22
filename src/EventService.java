import java.util.*;
import java.time.LocalDateTime;
public class EventService
{
    private List<Event> events = new ArrayList<>();

    public Event createEvent(String title, String description, LocalDateTime dateTime, UUID creatorId) {
        Event event = new Event(title, description, dateTime, creatorId);
        events.add(event);
        return event;
    }

    public boolean confirmAttendance(UUID eventId, UUID userId) {
        for (Event e : events) {
            if (e.getId().equals(eventId)) {
                return e.addParticipant(userId);
            }
        }
        return false;
    }

    public List<Event> getEventsCreatedBy(UUID userId) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            if (e.getCreatorId().equals(userId)) {
                result.add(e);
            }
        }
        return result;
    }

    public List<Event> getAllEvents() {
        return events;
    }
}

/*package mk.ukim.finki.eventapp.repository.impl;

import mk.ukim.finki.eventapp.model.Event;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryEventRepository {

    private final List<Event> events = new ArrayList<>(); // Листа за чување на настаните во меморија.
    private final AtomicLong idCounter = new AtomicLong(1); // Генерира уникатни ID за настаните.

    public List<Event> findAll() {
        return new ArrayList<>(events); // Враќа копија од листата за безбедност.
    }

    public Optional<Event> findById(Long id) {
        return events.stream()
                .filter(event -> event.getId().equals(id))
                .findFirst();
    }

    public List<Event> findByType(String type) {
        return events.stream()
                .filter(event -> event.getType().equals(type))
                .toList();
    }

   public List<Event> findByOrganizer(Long organizerId) {
        return events.stream()
                .filter(event -> event.getOrganizer() != null && event.getOrganizer().getId().equals(organizerId))
                .toList();
    }
    public List<Event> findByTimeRange(LocalDateTime start, LocalDateTime end) {
        return events.stream()
                .filter(event -> event.getStartTime().isAfter(start) && event.getEndTime().isBefore(end))
                .toList();
    }
    public boolean deleteById(Long id) {
        return events.removeIf(event -> event.getId().equals(id));
    }


     public Event save(Event event) {
        if (event.getId() == null) {
            event.setId(idCounter.getAndIncrement()); // Автоматски доделува ID ако е null.
        } else {
            deleteById(event.getId()); // Ако постои, го брише и го додава повторно.
        }
        events.add(event);
        return event;
    }


}


 */

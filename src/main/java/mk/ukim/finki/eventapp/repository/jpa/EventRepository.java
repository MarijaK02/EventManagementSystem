package mk.ukim.finki.eventapp.repository.jpa;

import mk.ukim.finki.eventapp.model.Event;
import mk.ukim.finki.eventapp.model.enumerations.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Override
    List<Event> findAll();

    @Override
    Optional<Event> findById(Long id);

    List<Event> findByType(Type type);

    List<Event> findByOrganizerId(Long organizerId);

    List<Event> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Event> findByStartTimeBeforeAndEndTimeAfter(LocalDateTime startTime, LocalDateTime endTime);

    List<Event> findByEndTimeBefore(LocalDateTime endTime);

    List<Event> findByStartTimeAfter(LocalDateTime startTime);

    List<Event> findAllByStartTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}

package mk.ukim.finki.eventapp.service.impl;




import jakarta.transaction.Transactional;
import mk.ukim.finki.eventapp.model.*;
import mk.ukim.finki.eventapp.model.enumerations.Type;
import mk.ukim.finki.eventapp.model.exceptions.EventNotFoundException;
import mk.ukim.finki.eventapp.model.exceptions.InvalidRatingException;
import mk.ukim.finki.eventapp.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.eventapp.repository.CommentRepository;
import mk.ukim.finki.eventapp.repository.EventRepository;
import mk.ukim.finki.eventapp.repository.RateRepository;
import mk.ukim.finki.eventapp.repository.UserRepository;
import mk.ukim.finki.eventapp.service.EventService;
import mk.ukim.finki.eventapp.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    private final CommentRepository commentRepository;

    private final RateRepository rateRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository, UserService userService, CommentRepository commentRepository, RateRepository rateRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.rateRepository = rateRepository;
    }

public Event createEvent(EventRequestDTO dto) {
    Type type;
    try {
        type = Type.valueOf(dto.getType().toUpperCase());
    } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Invalid event type: " + dto.getType());
    }

    Event event = Event.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .startTime(dto.getStartTime())
            .endTime(dto.getEndTime())
            .location(dto.getLocation())
            .status(dto.getStatus())
            .type(type)
            .createdAt(LocalDateTime.now())
            .organizer(userService.findById(dto.getOrganizerId()))
            .build();

    return eventRepository.save(event);
}



    @Override
    public List<Event> getEventsByType(Type type) {
        return eventRepository.findByType(type);
    }



    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public boolean deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;  // настанот е успешно избришан
        } else {
            return false;  // настанот со даденото ID не постои
        }
    }

    @Override
    public List<Event> getCurrentEvents() {
        LocalDateTime now = LocalDateTime.now();
        return eventRepository.findByStartTimeBeforeAndEndTimeAfter(now, now);
    }

    @Override
    public List<Event> getPastEvents() {
        LocalDateTime now = LocalDateTime.now();
        return eventRepository.findByEndTimeBefore(now);
    }

    @Override
    public List<Event> getUpcomingEvents() {
        LocalDateTime now = LocalDateTime.now();
        return eventRepository.findByStartTimeAfter(now);
    }

    @Override
    public Event getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));
    }

    @Override
    public void registerForEvent(Long eventId, User user) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + eventId));


        if (event.getParticipants().contains(user)) {
            throw new RuntimeException("User already registered for this event");
        }


        event.getParticipants().add(user);


        user.getEvents().add(event);


        eventRepository.save(event);
        userRepository.save(user);
    }

    @Override

    public List<Event> getEventsForToday(LocalDateTime startOfDay, LocalDateTime endOfDay) {
        return eventRepository.findAllByStartTimeBetween(startOfDay, endOfDay);
    }


    @Override
    @Transactional
    public Event addCommentToEvent(Long id, String username, String commentText) {
        Event event = this.eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Event not found!"));
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new InvalidUsernameException(username));

        Comment newComment = new Comment(commentText);
        newComment.setEvent(event);
        this.commentRepository.save(newComment);

        user.getComments().add(newComment);
        this.userRepository.save(user);

        event.getComments().add(newComment);
        this.eventRepository.save(event);

        return getEventById(event.getId());

    }


// @Transactional
// public Event addRatingToEvent(Long eventId, Integer rate) {
//
//     Event event = this.eventRepository.findById(eventId)
//             .orElseThrow(() -> new EventNotFoundException("Event not found!"));
//
//     if (rate < 1 || rate > 5) {
//         throw new InvalidRatingException("Rating must be between 1 and 5");
//     }
//
//
//     Rate newRate = new Rate(rate);
//     newRate.setEvent(event);
//     this.rateRepository.save(newRate);
//
//
//     event.getRates().add(newRate);
//
//
//     int sumRates = event.getRates().stream().mapToInt(Rate::getRate).sum();
//     event.setRating((double)sumRates / event.getRates().size());
//
//
//     this.eventRepository.save(event);
//
//     return event;
// }

    @Transactional
    public Event addRatingToEvent(Long eventId, Integer rate, String username) {
        Event event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found!"));

        if (rate < 1 || rate > 5) {
            throw new InvalidRatingException("Rating must be between 1 and 5");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Rate newRate = new Rate(rate);
        newRate.setEvent(event);
        newRate.setUser(user);

        this.rateRepository.save(newRate);

        event.getRates().add(newRate);

        int sumRates = event.getRates().stream().mapToInt(Rate::getRate).sum();
        event.setRating((double) sumRates / event.getRates().size());

        this.eventRepository.save(event);

        return event;
    }

    @Override
    public List<Comment> getCommentsForEvent(Long wineryId) {
        Event winery = this.eventRepository.findById(wineryId).orElseThrow(() -> new EventNotFoundException("Event not found"));
        return commentRepository.findAllByEvent(winery);
    }


}

package mk.ukim.finki.eventapp.service.impl;




import jakarta.transaction.Transactional;
import mk.ukim.finki.eventapp.model.Comment;
import mk.ukim.finki.eventapp.model.Event;
import mk.ukim.finki.eventapp.model.Rate;
import mk.ukim.finki.eventapp.model.User;
import mk.ukim.finki.eventapp.model.enumerations.Type;
import mk.ukim.finki.eventapp.model.exceptions.EventNotFoundException;
import mk.ukim.finki.eventapp.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.eventapp.repository.jpa.CommentRepository;
import mk.ukim.finki.eventapp.repository.jpa.EventRepository;
import mk.ukim.finki.eventapp.repository.jpa.RateRepository;
import mk.ukim.finki.eventapp.repository.jpa.UserRepository;
import mk.ukim.finki.eventapp.service.EventService;
import mk.ukim.finki.eventapp.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

//    @Autowired
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

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getEventsByType(Type type) {
        return eventRepository.findByType(type);
    }

//    @Override
//    public List<Event> getUpcomingEvents(int days) {
//        return eventRepository.findUpcomingEvents(days);
//    }

    // Пребарување на настани кои ќе се случат во следните X денови
//    public List<Event> getUpcomingEvents(int days) {
//        // Пресметување на датумот за X денови од денес
//        LocalDateTime futureDate = LocalDateTime.now().plusDays(days);
//        return eventRepository.findUpcomingEvents(futureDate);
//    }

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
        // Пребарување на настанот по ID
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + eventId));

        // Проверка дали корисникот веќе е регистриран за настанот
        if (event.getParticipants().contains(user)) {
            throw new RuntimeException("User already registered for this event");
        }

        // Додавање на корисникот во списокот на учесници
        event.getParticipants().add(user);

        // Додавање на настанот во списокот на настани за корисникот (ако е потребно)
        user.getEvents().add(event);

        // Спремање на промените
        eventRepository.save(event);  // Спремање на изменетиот настан
        userRepository.save(user);    // Спремање на изменетиот корисник
    }

    @Override

    public List<Event> getEventsForToday(LocalDateTime startOfDay, LocalDateTime endOfDay) {
        // Пребарај во базата за настаните
        return eventRepository.findAllByStartTimeBetween(startOfDay, endOfDay);
    }

    @Override
    public Optional<Event> addRatingToEvent(Long id, Integer rating) {
        return Optional.empty();
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

    @Transactional
    @Override
    public Event addRatingToWinery(Long eventId, Integer rate)
    {
        Event event= this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found!"));

        Rate newRate = new Rate(rate);
        newRate.setEvent(event);
        this.rateRepository.save(newRate);

//        user.getRates().add(newRate);
//        this.userService.save(user);

        event.getRates().add(newRate);
        int sumRates = event.getRates().stream().mapToInt(Rate::getRate).sum();
        event.setRating((double)sumRates/event.getRates().size());

        this.eventRepository.save(event);

        return getEventById(event.getId());
    }
    @Override
    public List<Comment> getCommentsForEvent(Long wineryId) {
        Event winery = this.eventRepository.findById(wineryId).orElseThrow(() -> new EventNotFoundException("Event not found"));
        return commentRepository.findAllByEvent(winery);
    }

}

package mk.ukim.finki.eventapp.service;


import jakarta.transaction.Transactional;
import mk.ukim.finki.eventapp.model.Comment;
import mk.ukim.finki.eventapp.model.Event;
import mk.ukim.finki.eventapp.model.User;
import mk.ukim.finki.eventapp.model.enumerations.Type;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface EventService {
    Event createEvent(Event event);

    List<Event> getEventsByType(Type type);

//    List<Event> getUpcomingEvents(int days);

    List<Event> getAllEvents();

    boolean deleteEvent(Long id);


    List<Event> getCurrentEvents();

    List<Event> getPastEvents();

    List<Event> getUpcomingEvents();

    Event getEventById(Long id);

    void registerForEvent(Long id, User user);

    List<Event> getEventsForToday(LocalDateTime startOfDay, LocalDateTime endOfDay);

    Event addCommentToEvent(Long id, String username, String comment);

    Optional<Event> addRatingToEvent(Long id, Integer rating);

    @Transactional
    Event addRatingToWinery(Long wineryId, Integer rate);

    List<Comment> getCommentsForEvent(Long wineryId);
}

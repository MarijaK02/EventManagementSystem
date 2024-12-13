package mk.ukim.finki.eventapp.web.controller;

import mk.ukim.finki.eventapp.model.Event;
import mk.ukim.finki.eventapp.model.User;
import mk.ukim.finki.eventapp.model.exceptions.EventNotFoundException;
import mk.ukim.finki.eventapp.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.eventapp.service.EventService;
import mk.ukim.finki.eventapp.model.enumerations.Type;
import mk.ukim.finki.eventapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/events","/"})
public class EventRestController {

    private final EventService eventService;

    private final UserService userService;

    @Autowired
    public EventRestController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    // Додавање нов настан
    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event) {
        event.setCreatedAt(LocalDateTime.now()); // Автоматско поставување на тековното време на креирањето на настанот
        return eventService.createEvent(event);
    }

    // Пребарување на настани по тип
    @GetMapping("/type/{type}")
    public List<Event> getEventsByType(@PathVariable Type type) {
        return eventService.getEventsByType(type);
    }

    // Пребарување на сите настани
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }


    // Избриши настан
    @DeleteMapping("/{id}")
    public boolean deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }

    @GetMapping("/upcoming")
    public List<Event> getUpcomingEvents() {
        return eventService.getUpcomingEvents();
    }

    @GetMapping("/past")
    public List<Event> getPastEvents() {
        return eventService.getPastEvents();
    }

    @GetMapping("/current")
    public List<Event> getCurrentEvents() {
        return eventService.getCurrentEvents();
    }
    @GetMapping("/{id}/details")
    public Event getEventDetails(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/today")
    public List<Event> getTodayEvents() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);


        return eventService.getEventsForToday(startOfDay, endOfDay);
    }

    @PostMapping("/{eventId}/register")
    public ResponseEntity<String> registerForEvent(@PathVariable Long eventId, @RequestBody User user) {
        try {
            eventService.registerForEvent(eventId, user);
            return ResponseEntity.ok("User successfully registered for the event");
        } catch (EventNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/users/{userId}/favorites")
    public List<Event> getFavoriteEvents(@PathVariable Long userId) {
        return userService.getFavoriteEvents(userId);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Object> getCommentsForEvent(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(eventService.getCommentsForEvent(id), HttpStatus.OK);
        } catch (EventNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
        @PostMapping("/{eventId}/comments")
        public ResponseEntity<String> commentEvent(@PathVariable Long id, @RequestParam String username, @RequestParam String comment){
            try{
                Event event = eventService.addCommentToEvent(id, username, comment);
                return ResponseEntity.ok("Comment added successfully");
            } catch(EventNotFoundException | InvalidUsernameException ex){
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
        }

    @PostMapping("/add-rating")
    public ResponseEntity<String> rateEvent(@PathVariable Long id, @RequestParam Integer rate){
        try{
            Event event = eventService.addRatingToWinery(id, rate);
            return ResponseEntity.ok("Rate added successfully");
        } catch(EventNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}


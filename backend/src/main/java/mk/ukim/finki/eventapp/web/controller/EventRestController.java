package mk.ukim.finki.eventapp.web.controller;

import mk.ukim.finki.eventapp.model.Event;
import mk.ukim.finki.eventapp.model.EventRequestDTO;
import mk.ukim.finki.eventapp.model.User;
import mk.ukim.finki.eventapp.model.exceptions.EventNotFoundException;
import mk.ukim.finki.eventapp.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.eventapp.model.exceptions.UserNotFoundException;
import mk.ukim.finki.eventapp.service.EventService;
import mk.ukim.finki.eventapp.model.enumerations.Type;
import mk.ukim.finki.eventapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/events","/"})
@Validated
@CrossOrigin(origins="*")
public class EventRestController {

    private final EventService eventService;

    private final UserService userService;

    @Autowired
    public EventRestController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }
  @PostMapping("/create")
  public ResponseEntity<?> createEvent(@RequestBody EventRequestDTO eventRequestDTO) {
      try {
          Event createdEvent = eventService.createEvent(eventRequestDTO);
          return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
      } catch (IllegalArgumentException e) {

          return ResponseEntity.badRequest().body(e.getMessage());
      } catch (Exception e) {

          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error: " + e.getMessage());
      }
  }


    @GetMapping("/type/{type}")
    public List<Event> getEventsByType(@PathVariable Type type) {
        return eventService.getEventsByType(type);
    }


    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }



    @DeleteMapping("/{id}/delete")
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
    public ResponseEntity<String> registerForEvent(@PathVariable Long eventId, @RequestParam String username) {
        try {
            User user = userService.findByUsername(username);
            eventService.registerForEvent(eventId, user);
            return ResponseEntity.ok("User successfully registered for the event");
        } catch (EventNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/users/{userId}/favorites")
    public ResponseEntity<?> getFavoriteEvents(@PathVariable Long userId) {
        try {
            List<Event> favorites = userService.getFavoriteEvents(userId);
            return ResponseEntity.ok(favorites);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
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
        public ResponseEntity<String> commentEvent(@PathVariable Long eventId, @RequestParam String username, @RequestParam String comment){
            try{
                Event event = eventService.addCommentToEvent(eventId, username, comment);
                return ResponseEntity.ok("Comment added successfully");
            } catch(EventNotFoundException | InvalidUsernameException ex){
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
        }

    @PostMapping("{id}/add-rating")
    public ResponseEntity<String> rateEvent(@PathVariable Long id, @RequestParam Integer rate, @RequestParam String username){
        try{
            Event event = eventService.addRatingToEvent(id, rate, username);
            return ResponseEntity.ok("Rate added successfully");
        } catch(EventNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}


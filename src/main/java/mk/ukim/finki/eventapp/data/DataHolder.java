/*package mk.ukim.finki.eventapp.data;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.eventapp.model.Comment;
import mk.ukim.finki.eventapp.model.User;
import mk.ukim.finki.eventapp.model.Event;
import mk.ukim.finki.eventapp.model.enumerations.Role;
import mk.ukim.finki.eventapp.model.enumerations.Type;
import mk.ukim.finki.eventapp.repository.jpa.CommentRepository;
import mk.ukim.finki.eventapp.repository.jpa.UserRepository;
import mk.ukim.finki.eventapp.repository.jpa.EventRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataHolder {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;


    public DataHolder(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;

    }

    @PostConstruct
    public void init() {
        // Проверка дали има корисници во базата
        if (userRepository.count() == 0) {
            User guest1 = new User(null, "guest1", "guest1", Role.GUEST, null);
            User guest2 = new User(null, "guest2", "guest2", Role.GUEST, null);
            User booker1 = new User(null, "booker1", "booker1", Role.BOOKER, null);
            User admin = new User(null, "admin", "admin", Role.BOOKER, null);

            userRepository.saveAll(List.of(guest1, guest2, booker1, admin));
        }

        // Проверка дали има настани во базата
        if (eventRepository.count() == 0) {
            List<User> users = userRepository.findAll();

            Event event1 = new Event(
                    null,
                    "Music Concert",
                    "Live music by famous artists",
                    LocalDateTime.of(2024, 12, 10, 20, 0),
                    LocalDateTime.of(2024, 12, 10, 23, 0),
                    "City Hall",
                    Type.CITY,
                    users.get(2), // Booker организатор
                    null
            );

            Event event2 = new Event(
                    null,
                    "University Seminar",
                    "Academic seminar on AI advancements",
                    LocalDateTime.of(2024, 12, 15, 10, 0),
                    LocalDateTime.of(2024, 12, 15, 12, 0),
                    "University Auditorium",
                    Type.UNIVERSITY,
                    users.get(2), // Booker организатор
                    null
            );

            Event event3 = new Event(
                    null,
                    "Art Exhibition",
                    "Exhibition of local artwork",
                    LocalDateTime.of(2024, 12, 20, 18, 0),
                    LocalDateTime.of(2024, 12, 20, 21, 0),
                    "Art Gallery",
                    Type.CITY,
                    users.get(2), // Booker организатор
                    null
            );

            eventRepository.saveAll(List.of(event1, event2, event3));

            // Додавање коментари за настаните
//            Comment comment1 = new Comment(null, "Amazing concert!", 5, users.get(0), event1);
//            Comment comment2 = new Comment(null, "Very insightful seminar", 4, users.get(1), event2);
//            Comment comment3 = new Comment(null, "Incredible artwork on display", 5, users.get(0), event3);
//
//            commentRepository.saveAll(List.of(comment1, comment2, comment3));
//            System.out.println("Comments saved successfully!");

        }
    }
}
*/
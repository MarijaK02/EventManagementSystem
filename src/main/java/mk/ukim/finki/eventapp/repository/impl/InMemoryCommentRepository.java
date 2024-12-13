/*package mk.ukim.finki.eventapp.repository.impl;

import mk.ukim.finki.eventapp.model.Comment;
import mk.ukim.finki.eventapp.model.Event;
import mk.ukim.finki.eventapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryCommentRepository {

    private final Map<Long, Comment> comments = new HashMap<>();
    private final Map<Long, List<Comment>> eventComments = new HashMap<>();
    private final Map<Long, List<Comment>> userComments = new HashMap<>();
    private Long idCounter = 1L;

    // Додавање на коментар
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            comment.setId(idCounter++);
        }
        comments.put(comment.getId(), comment);

        // Добавување на коментар во листата на коментари за настанот
        eventComments.computeIfAbsent(comment.getEvent().getId(), k -> new ArrayList<>()).add(comment);

        // Добавување на коментар во листата на коментари за корисникот
        userComments.computeIfAbsent(comment.getUser().getId(), k -> new ArrayList<>()).add(comment);

        return comment;
    }

    // Пребарување на коментари за конкретен настан
    public List<Comment> findByEvent(Event event) {
        return eventComments.getOrDefault(event.getId(), new ArrayList<>());
    }

    // Пребарување на коментари за конкретен корисник
    public List<Comment> findByUser(User user) {
        return userComments.getOrDefault(user.getId(), new ArrayList<>());
    }

    // Пребарување на сите коментари
    public List<Comment> findAll() {
        return new ArrayList<>(comments.values());
    }

    // Пребарување на коментар по ID
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(comments.get(id));
    }

    // Брисање на коментар
    public boolean deleteById(Long id) {
        Comment comment = comments.remove(id);
        if (comment != null) {
            eventComments.getOrDefault(comment.getEvent().getId(), new ArrayList<>()).remove(comment);
            userComments.getOrDefault(comment.getUser().getId(), new ArrayList<>()).remove(comment);
            return true;
        }
        return false;
    }
}
 */
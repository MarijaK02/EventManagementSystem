/*package mk.ukim.finki.eventapp.service;

import mk.ukim.finki.eventapp.model.Comment;
import mk.ukim.finki.eventapp.model.Event;
import mk.ukim.finki.eventapp.model.User;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    // Додавање нов коментар
    Comment addComment(String content, int rating, User user, Event event);

    // Пребарување на коментари за одреден настан
    List<Comment> getCommentsByEvent(Event event);

    // Пребарување на коментари за одреден корисник
    List<Comment> getCommentsByUser(User user);

    // Избриши коментар
    boolean deleteComment(Long id);

    // Измени коментар
    Optional<Comment> updateComment(Long id, String newContent, int newRating);

    List<Comment> findAllComments();
}

 */
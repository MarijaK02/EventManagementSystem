package mk.ukim.finki.eventapp.repository.jpa;


import mk.ukim.finki.eventapp.model.Comment;
import mk.ukim.finki.eventapp.model.Event;
import mk.ukim.finki.eventapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByEvent(Event event);  // Пребарување на коментари по настан

    List<Comment> findByUser(User user); // пребарување на коментари според корисник

    List<Comment> findAllByEvent(Event event);
}
/*package mk.ukim.finki.eventapp.service.impl;

import mk.ukim.finki.eventapp.model.Comment;
import mk.ukim.finki.eventapp.model.Event;
import mk.ukim.finki.eventapp.model.User;
import mk.ukim.finki.eventapp.repository.jpa.CommentRepository;
import mk.ukim.finki.eventapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Додавање нов коментар
    @Override
    public Comment addComment(String content, int rating, User user, Event event) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setRating(rating);
        comment.setUser(user);
        comment.setEvent(event);
        return commentRepository.save(comment);
    }

    // Пребарување на коментари за одреден настан
    @Override
    public List<Comment> getCommentsByEvent(Event event) {
        return commentRepository.findByEvent(event);
    }

    // Пребарување на коментари за одреден корисник
    @Override
    public List<Comment> getCommentsByUser(User user) {
        return commentRepository.findByUser(user);
    }

    // Избриши коментар
    @Override
    public boolean deleteComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            commentRepository.delete(comment.get());
            return true;
        }
        return false;
    }

    // Измени коментар
    @Override
    public Optional<Comment> updateComment(Long id, String newContent, int newRating) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setContent(newContent);
            comment.setRating(newRating);
            commentRepository.save(comment);
            return Optional.of(comment);
        }
        return Optional.empty();
    }

    @Override
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }
}

 */
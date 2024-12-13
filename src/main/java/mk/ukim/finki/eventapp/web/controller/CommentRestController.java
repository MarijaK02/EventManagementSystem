/*package mk.ukim.finki.eventapp.web.controller;


import mk.ukim.finki.eventapp.model.Comment;
import mk.ukim.finki.eventapp.model.Event;
import mk.ukim.finki.eventapp.model.User;
import mk.ukim.finki.eventapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentRestController {

    private final CommentService commentService;

    @Autowired
    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.findAllComments();
    }

    // Додавање нов коментар
    @PostMapping
    public Comment addComment(@RequestParam String content, @RequestParam int rating,
                              @RequestParam User user, @RequestParam Event event) {
        return commentService.addComment(content, rating, user, event);
    }

    // Пребарување на коментари за настан
    @GetMapping("/event/{eventId}")
    public List<Comment> getCommentsByEvent(@PathVariable Long eventId) {
        Event event = new Event();
        event.setId(eventId); // Може да се вчита настан од базата, зависи од имплементацијата
        return commentService.getCommentsByEvent(event);
    }

    // Пребарување на коментари за корисник
    @GetMapping("/user/{userId}")
    public List<Comment> getCommentsByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId); // Може да се вчита корисник од базата
        return commentService.getCommentsByUser(user);
    }
}

 */
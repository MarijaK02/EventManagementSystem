package mk.ukim.finki.eventapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.eventapp.model.enumerations.Role;


import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "event_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // "GUEST" or "BOOKER"


    @ManyToMany
    @JoinTable(name = "user_events")
    @JsonIgnore
    private List<Event> events;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Event> favoriteEvents = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

}

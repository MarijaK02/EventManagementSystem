package mk.ukim.finki.eventapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.eventapp.model.enumerations.Role;
import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.eventapp.model.enumerations.UserStatus;


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

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Username is required")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; //

    @ManyToMany(mappedBy = "participants")
    @JsonIgnore
    private List<Event> events;

    @Enumerated(EnumType.STRING)
    private UserStatus status;



    @ManyToMany
    @JoinTable(name = "user_favorite_events")
    @JsonIgnore
    private List<Event> favoriteEvents = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;


    public User(String username, String name, String surname, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.status = UserStatus.LOGGED_OUT;
        this.comments = new ArrayList<>();
    }


}

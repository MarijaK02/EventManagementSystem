package mk.ukim.finki.eventapp.service.impl;

import mk.ukim.finki.eventapp.model.Event;
import mk.ukim.finki.eventapp.model.User;
import mk.ukim.finki.eventapp.model.exceptions.UserNotFoundException;
import mk.ukim.finki.eventapp.repository.jpa.UserRepository;
import mk.ukim.finki.eventapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Event> getFavoriteEvents(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getFavoriteEvents(); // Враќате список на омилени настани
        } else {
            throw new UserNotFoundException("User not found with id: " + userId); // Ако не постои корисник
        }
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }


    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
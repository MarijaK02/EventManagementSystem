/*package mk.ukim.finki.eventapp.repository.impl;


import mk.ukim.finki.eventapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUserRepository {

    private final List<User> users = new ArrayList<>(); // Листа што ги чува корисниците во меморија.
    private final AtomicLong idCounter = new AtomicLong(1); // Генерира уникатни ID за корисниците.

    public List<User> findAll() {
        return new ArrayList<>(users); // Враќа копија од листата за безбедност.
    }

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idCounter.getAndIncrement()); // Автоматски доделува ID ако е null.
        } else {
            deleteById(user.getId()); // Ако постои, го брише и го додава повторно.
        }
        users.add(user);
        return user;
    }

    public boolean deleteById(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}

 */
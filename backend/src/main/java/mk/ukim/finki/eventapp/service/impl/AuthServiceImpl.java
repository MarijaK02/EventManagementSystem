package mk.ukim.finki.eventapp.service.impl;

import mk.ukim.finki.eventapp.model.User;
import mk.ukim.finki.eventapp.model.enumerations.Role;
import mk.ukim.finki.eventapp.model.enumerations.UserStatus;
import mk.ukim.finki.eventapp.model.exceptions.InvalidCredentialsException;
import mk.ukim.finki.eventapp.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.eventapp.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.eventapp.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.eventapp.repository.UserRepository;
import mk.ukim.finki.eventapp.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;


    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    private boolean credentialsInvalid(String username, String password) {
        return username == null || password == null || username.isEmpty() || password.isEmpty();
    }

    @Override
    public User login(String username, String password) {
        if (credentialsInvalid(username, password)) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        User user =  userRepository.findByUsername(username).orElseThrow(() -> new InvalidUsernameException(username));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        user.setStatus(UserStatus.LOGGED_IN);
        return this.userRepository.save(user);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if (credentialsInvalid(username, password)) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if(this.userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }


        String passwordEncoder = this.passwordEncoder.encode(password);

        User user = new User(username, name, surname, passwordEncoder);
        return userRepository.save(user);
    }

    @Override
    public void logout(String username) {
        User user = this.userRepository.findByUsername(username).get();
        user.setStatus(UserStatus.LOGGED_OUT);
        this.userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public void createAdminIfNotExists() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            admin.setName("Admin");
            admin.setSurname("Adminov");
            admin.setEmail("admin@example.com");
            userRepository.save(admin);
        }
    }

    @Override
    public User changePassword(String username, String oldPassword, String newPassword, String repeatNewPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidUsernameException(username));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new InvalidCredentialsException("Old password is incorrect");
        }

        if (!newPassword.equals(repeatNewPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

}



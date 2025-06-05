package mk.ukim.finki.eventapp.service;

import mk.ukim.finki.eventapp.model.User;

import java.util.List;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword,
                 String name, String surname);
    void logout(String username);

    List<User> findAll();

    void createAdminIfNotExists();

    User changePassword(String username, String oldPassword, String newPassword, String repeatNewPassword);

}

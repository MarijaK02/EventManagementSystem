package mk.ukim.finki.eventapp.web.controller;

import mk.ukim.finki.eventapp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.eventapp.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.eventapp.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@Validated
@CrossOrigin(origins="*")
public class RegisterController {
    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("")
    public ResponseEntity<String> register(@RequestParam String username,
                                           @RequestParam String password,
                                           @RequestParam String repeatPassword,
                                           @RequestParam String name,
                                           @RequestParam String surname) {
        try {
            this.authService.register(username, password, repeatPassword, name, surname);
            return ResponseEntity.ok("Registration successful");

        } catch (PasswordsDoNotMatchException | InvalidArgumentsException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}


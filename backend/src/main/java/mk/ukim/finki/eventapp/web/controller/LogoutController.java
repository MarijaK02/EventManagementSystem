package mk.ukim.finki.eventapp.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.eventapp.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
@Validated
@CrossOrigin(origins="*")
public class LogoutController {
    private final AuthService authService;

    public LogoutController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping
    public ResponseEntity<String> logout(HttpServletRequest request) {
        authService.logout(request.getParameter("username"));
        return ResponseEntity.ok("Logout successful");

    }
}
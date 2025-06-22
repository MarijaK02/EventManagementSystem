package mk.ukim.finki.eventapp.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.eventapp.model.User;
import mk.ukim.finki.eventapp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.eventapp.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Validated
@CrossOrigin(origins="*")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("")
    public ResponseEntity<Object> login(HttpServletRequest request){
        try{
            User user = this.authService.login(request.getParameter("username"), request.getParameter("password"));
            return ResponseEntity.ok(user);
        }catch(InvalidUserCredentialsException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }


}
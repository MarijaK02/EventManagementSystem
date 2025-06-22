package mk.ukim.finki.eventapp.data;

import mk.ukim.finki.eventapp.service.AuthService;
import mk.ukim.finki.eventapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;

    private final AuthService authService;

    public DataInitializer(UserService userService,AuthService authService) {
        this.userService = userService;
       this.authService = authService;
    }

    @Override
    public void run(String... args) throws Exception {
        authService.createAdminIfNotExists();
    }
}

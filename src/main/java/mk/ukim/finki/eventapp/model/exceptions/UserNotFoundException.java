package mk.ukim.finki.eventapp.model.exceptions;

public class UserNotFoundException extends RuntimeException {

    // Дефинирање на конструктори
    public UserNotFoundException(String message) {
        super(message); // Повик на конструкторот на родителската класа (RuntimeException)
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause); // Повик на конструкторот на родителската класа со причината за исклучок
    }
}
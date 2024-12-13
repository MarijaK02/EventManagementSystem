package mk.ukim.finki.eventapp.model.exceptions;

public class EventNotFoundException extends RuntimeException {

    // Конструктор за само порака
    public EventNotFoundException(String message) {
        super(message);  // Пораката што ќе се прикаже кога ќе се фрли исклучокот
    }

    // Конструктор за порака и причина за исклучокот
    public EventNotFoundException(String message, Throwable cause) {
        super(message, cause);  // Порака и причина за исклучокот
    }
}

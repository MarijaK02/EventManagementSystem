package mk.ukim.finki.eventapp.model.exceptions;

public class InvalidUsernameException extends RuntimeException{
    public InvalidUsernameException(String username) {
        super(String.format("Корисничко име %s не постои!", username));
    }
}

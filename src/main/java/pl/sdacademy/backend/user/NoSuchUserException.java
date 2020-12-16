package pl.sdacademy.backend.user;

public class NoSuchUserException extends Exception{
    public NoSuchUserException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    } // potrzebne?
    public NoSuchUserException(String errorMessage) {
        super(errorMessage);
    }
}

package pl.sdacademy.backend.guest;

public class NoSuchGuestException extends Exception {
    public NoSuchGuestException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    } // potrzebne?
    public NoSuchGuestException(String errorMessage) {
        super(errorMessage);
    }
}

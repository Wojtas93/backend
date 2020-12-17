package pl.sdacademy.backend.guest;

import java.util.NoSuchElementException;

public class NoSuchGuestException extends NoSuchElementException {
    public NoSuchGuestException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    } // potrzebne?
    public NoSuchGuestException(String errorMessage) {
        super(errorMessage);
    }
}

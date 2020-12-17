package pl.sdacademy.backend.user;

import java.util.NoSuchElementException;

public class NoSuchUserException extends NoSuchElementException {
    public NoSuchUserException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    } // potrzebne?
    public NoSuchUserException(String errorMessage) {
        super(errorMessage);
    }
}

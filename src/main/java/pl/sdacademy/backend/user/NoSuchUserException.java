package pl.sdacademy.backend.user;

import java.util.NoSuchElementException;

public class NoSuchUserException extends NoSuchElementException {
     // potrzebne?
    public NoSuchUserException(String errorMessage) {
        super(errorMessage);
    }
}

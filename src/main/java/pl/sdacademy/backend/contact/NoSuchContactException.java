package pl.sdacademy.backend.contact;

import java.util.NoSuchElementException;

public class NoSuchContactException extends NoSuchElementException {
    public NoSuchContactException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    } // potrzebne?
    public NoSuchContactException(String errorMessage) {
        super(errorMessage);
    }
}

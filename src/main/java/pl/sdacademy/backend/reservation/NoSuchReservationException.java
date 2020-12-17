package pl.sdacademy.backend.reservation;

import java.util.NoSuchElementException;

public class NoSuchReservationException extends NoSuchElementException {
    public NoSuchReservationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    } // potrzebne?
    public NoSuchReservationException(String errorMessage) {
        super(errorMessage);
    }
}

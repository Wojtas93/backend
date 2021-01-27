package pl.sdacademy.backend.reservation;

import java.util.NoSuchElementException;

public class NoSuchReservationException extends NoSuchElementException {
    public NoSuchReservationException(String errorMessage) {
        super(errorMessage);
    }
}

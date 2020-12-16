package pl.sdacademy.backend.reservation;

public class NoSuchReservationException extends Exception {
    public NoSuchReservationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    } // potrzebne?
    public NoSuchReservationException(String errorMessage) {
        super(errorMessage);
    }
}

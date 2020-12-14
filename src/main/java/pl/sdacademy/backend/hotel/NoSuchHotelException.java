package pl.sdacademy.backend.hotel;

public class NoSuchHotelException extends Exception {
    public NoSuchHotelException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public NoSuchHotelException(String errorMessage) {
        super(errorMessage);
    }
}

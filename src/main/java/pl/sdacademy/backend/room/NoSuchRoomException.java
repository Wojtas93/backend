package pl.sdacademy.backend.room;

public class NoSuchRoomException extends Exception {
    public NoSuchRoomException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public NoSuchRoomException(String errorMessage) {
        super(errorMessage);
    }
}

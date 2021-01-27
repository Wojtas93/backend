package pl.sdacademy.backend.room;

import java.util.NoSuchElementException;

public class NoSuchRoomException extends NoSuchElementException {
    public NoSuchRoomException(String errorMessage) {
        super(errorMessage);
    }
}

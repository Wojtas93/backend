package pl.sdacademy.backend.Errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sdacademy.backend.reservation.NoSuchReservationException;
import pl.sdacademy.backend.room.NoSuchRoomException;
import pl.sdacademy.backend.user.NoSuchUserException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ResponseMessage> handle(MethodArgumentNotValidException e) {
        LOGGER.info("Response Messages in body");
        return e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ResponseMessage(fieldError.getField() + ": " + fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage handle(ArrayIndexOutOfBoundsException e) {
        LOGGER.info("Get ArrayIndexOutOfBoundsException: " + e.getMessage());
        return new ResponseMessage("Get ArrayIndexOutOfBoundsException: " + e.getMessage());
    }

    @ExceptionHandler(NoSuchReservationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage handle(NoSuchReservationException e) {
        LOGGER.info("Couldn't find this reservation, check reservation number");
        return new ResponseMessage("Couldn't find this reservation, check reservation Number");
    }

    @ExceptionHandler(NoSuchUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage handle(NoSuchUserException e) {
        LOGGER.info("Couldn't find this user, check user id and name");
        return new ResponseMessage("Couldn't find this user, check user id and name");
    }

    @ExceptionHandler(NoSuchRoomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage handle(NoSuchRoomException e) {
        LOGGER.info("Couldn't find this room, check room number and id");
        return new ResponseMessage("Couldn't find this room, check room number and id");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage handle(Exception e) {
        LOGGER.info("Get unknown exception " + e.getMessage());
        return new ResponseMessage("Get unknown exception " + e.getMessage());
    }
}

package pl.sdacademy.backend.reservation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.backend.TextRespone.TextResponse;
import pl.sdacademy.backend.guest.*;
import pl.sdacademy.backend.room.Room;
import pl.sdacademy.backend.room.RoomController;

import java.util.Collections;
import java.util.List;

@RestController
public class ReservationController {
    private ReservationRepository reservationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
    private RoomController roomController;
    private GuestController guestController;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public ResponseEntity<ReservationResponseDto> get() throws NoSuchReservationException {
        try {
            return ResponseEntity.ok(new ReservationResponseDto(reservationRepository.findAll()));
        } catch (NoSuchReservationException e) {
            LOGGER.info("DB is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ReservationResponseDto(Collections.emptyList()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> get(@PathVariable Long id) throws NoSuchReservationException {
        try {
            return ResponseEntity.ok(reservationRepository.getOne(id));
        } catch (NoSuchReservationException e) {
            LOGGER.info("Couldn't find this reservation, check id");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/roomNr/{roomNr}")
    public ResponseEntity<Reservation> get(@PathVariable String roomNr) throws NoSuchReservationException {
        try {
            Room room = roomController.get(roomNr).getBody();
            return ResponseEntity.ok(reservationRepository.findByRoom(room).get());
        } catch (NoSuchReservationException e) {
            LOGGER.info("Couldn't find this reservation, check reservation Number");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/roomId/{roomId}")
    public ResponseEntity<Reservation> get(@PathVariable long roomId) throws NoSuchReservationException {
        try {
            Room room = roomController.get(roomId).getBody();
            return ResponseEntity.ok(reservationRepository.findByRoom(room).get());
        } catch (NoSuchReservationException e) {
            LOGGER.info("Couldn't find this reservation, check reservation Number");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/{guestFirstName}{guestLastName}")
    public ResponseEntity<Reservation> get(@PathVariable(required = false) String guestFirstName, @PathVariable String guestLastName) throws NoSuchReservationException {
        try {
            Guest guest;
            if (!guestFirstName.isEmpty()) {
                guest = guestController.get(guestFirstName, guestLastName).getBody();
            }else {
                guest = guestController.get(guestLastName).getBody();
            }
            return ResponseEntity.ok(reservationRepository.findByGuest(guest).get());
        } catch (NoSuchReservationException e) {
            LOGGER.info("Couldn't find this reservation, check reservation Number");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<TextResponse> update(@RequestBody Reservation reservationNew, @PathVariable long id) throws NoSuchReservationException {
        try {
            Reservation reservation = reservationRepository.getOne(id);
            reservation.setRoom(reservationNew.getRoom());
            reservation.setGuest(reservationNew.getGuest());
            reservation.setStartDate(reservationNew.getStartDate());
            reservation.setEndDate(reservationNew.getEndDate());
            reservation.setPaid(reservationNew.getPaid());
            reservationRepository.save(reservation);
            return ResponseEntity.ok(new TextResponse("Reservation updated"));
        } catch (NoSuchReservationException e) {
            LOGGER.info("Couldn't update this reservation");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TextResponse("Couldn't find this reservation"));
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TextResponse> delete(@PathVariable long id) throws NoSuchReservationException {
        try {
            reservationRepository.delete(reservationRepository.getOne(id));
            LOGGER.info("reservation deleted successful");
            return ResponseEntity.ok(new TextResponse("reservation deleted"));
        } catch (NoSuchReservationException e) {
            LOGGER.info("Couldn't delete this reservation");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TextResponse("Couldn't deleted this reservation"));
        }
    }


}

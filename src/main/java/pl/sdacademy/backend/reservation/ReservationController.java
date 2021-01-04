package pl.sdacademy.backend.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.backend.Errors.ResponseMessage;
import pl.sdacademy.backend.room.Room;
import pl.sdacademy.backend.room.RoomController;

import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationRepository reservationRepository;
    private RoomController roomController;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public ReservationResponseDto get() {
        return new ReservationResponseDto(reservationRepository.findAll());
    }

    @GetMapping("/admin")
    public List<Reservation> getAdmin() {
        return reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation get(@PathVariable Long id) {
        return reservationRepository.findById(id).get();
    }

    @GetMapping("/roomNr/{roomNr}")
    public Reservation get(@PathVariable String roomNr) {
        Room room = roomController.get(roomNr);
        return reservationRepository.findByRoom(room).get();

    }

    @GetMapping("/roomId/{roomId}")
    public Reservation get(@PathVariable long roomId) {
        Room room = roomController.get(roomId);
        return reservationRepository.findByRoom(room).get();
    }

    @GetMapping("/{userFirstName}/{userLastName}")
    public ReservationResponseDto get(@PathVariable(required = false) String userFirstName, @PathVariable String userLastName) {
        if (!userFirstName.isEmpty()) {
            return new ReservationResponseDto(reservationRepository.findByUser_FirstNameAndUser_LastNameOrderByStartDate(userFirstName, userLastName));
        } else {
            return new ReservationResponseDto(reservationRepository.findByUser_LastNameOrderByStartDate(userLastName));
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseMessage update(@RequestBody Reservation reservationNew, @PathVariable long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setRoom(reservationNew.getRoom());
        reservation.setUser(reservationNew.getUser());
        reservation.setStartDate(reservationNew.getStartDate());
        reservation.setEndDate(reservationNew.getEndDate());
        reservation.setPaid(reservationNew.getPaid());
        reservationRepository.save(reservation);
        return new ResponseMessage("Reservation updated");
    }

    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable long id) {
        reservationRepository.delete(reservationRepository.findById(id).get());
        return new ResponseMessage("Reservation deleted");

    }


}

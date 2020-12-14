package pl.sdacademy.backend.reservation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {
    ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservation")
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @GetMapping("/reservation/{id}")
    public Reservation getById(@PathVariable Long id) throws NoSuchReservationException {
        return reservationRepository.findById(id).orElseThrow(() -> new NoSuchReservationException("No Reservation was found with ID: " + id));
    }

    //public List<Reservation> getReservationsByRoom(Room room)

    //public List<LocalDate> getAvailableDatesByRoom(Room room, LocalDate endDate)

    //public List<LocalDate> getReservedDatesByRoom(Room room, LocalDate endDate)

    //public List<Room> getAvailableRoomByDates(LocalDate startDate, LocalDate endDate)

}

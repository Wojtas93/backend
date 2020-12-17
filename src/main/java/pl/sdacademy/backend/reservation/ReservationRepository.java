package pl.sdacademy.backend.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sdacademy.backend.guest.Guest;
import pl.sdacademy.backend.room.Room;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
Optional <Reservation> findByRoom (Room room);
Optional <Reservation> findByGuest(Guest guest);

}

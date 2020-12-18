package pl.sdacademy.backend.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sdacademy.backend.room.Room;
import pl.sdacademy.backend.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByRoom(Room room);

    List<Reservation> findByUser_LastNameOrderByStartDate(String lastName);

    List<Reservation> findByUser_FirstNameAndUser_LastNameOrderByStartDate(String firstName, String lastName);
}

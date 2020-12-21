package pl.sdacademy.backend;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sdacademy.backend.reservation.Reservation;
import pl.sdacademy.backend.reservation.ReservationRepository;
import pl.sdacademy.backend.room.Room;
import pl.sdacademy.backend.room.RoomRepository;
import pl.sdacademy.backend.user.User;
import pl.sdacademy.backend.user.UserRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DbInit {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoomRepository roomRepository;
    private ReservationRepository reservationRepository;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @PostConstruct
    public void onInit() {
        User user1 = new User(
                "user1",
                passwordEncoder.encode("Password1"),
                "user1@example.pl", "USER",
                "firstName", "lastName", null);
        userRepository.save(user1);
        User user2 = new User(
                "user2",
                passwordEncoder.encode("Password2"),
                "user2@example.pl", "USER",
                "firstName", "lastName", "4929486472865824");
        userRepository.save(user2);
        User user3 = new User(
                "user3",
                passwordEncoder.encode("Password3"),
                "user3@example.pl", "ADMIN",
                "firstName", "lastName", "5260982372346117");
        userRepository.save(user3);

        Room room1 = new Room("101", true, 2, BigDecimal.valueOf(150.00));
        roomRepository.save(room1);
        Room room2 = new Room("102", false, 4, BigDecimal.valueOf(252.50));
        roomRepository.save(room2);

        Reservation reservation1 = new Reservation(room1,
                user1,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(5),
                false);
        reservationRepository.save(reservation1);

        Reservation reservation2 = new Reservation(room2,
                user2,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(15),
                true);
        reservationRepository.save(reservation2);
    }
}

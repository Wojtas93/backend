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
                passwordEncoder.encode("pass1"),
                "USER",
                "firstName", "lastName");
        userRepository.save(user1);
        User user2 = new User(
                "user2",
                passwordEncoder.encode("pass2"),
                "USER",
                "firstName", "lastName");
        userRepository.save(user2);
        User user3 = new User(
                "user3",
                passwordEncoder.encode("pass3"),
                "ADMIN",
                "firstName", "lastName");
        userRepository.save(user3);

        Room room1 = new Room("101", true, 2, BigDecimal.valueOf(150.00));
        roomRepository.save(room1);
        Room room2 = new Room("102", false, 4, BigDecimal.valueOf(252.50));
        roomRepository.save(room2);

        Reservation reservation1 = new Reservation(room1,
                user1,
                LocalDate.of(2020, 12, 10),
                LocalDate.of(2020, 12, 10),
                false);
        reservationRepository.save(reservation1);

        Reservation reservation2 = new Reservation(room2,
                user2,
                LocalDate.of(2020, 12, 25),
                LocalDate.of(2020, 12, 31),
                true);
        reservationRepository.save(reservation2);
    }
}

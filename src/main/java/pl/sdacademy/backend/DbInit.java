package pl.sdacademy.backend;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sdacademy.backend.guest.Guest;
import pl.sdacademy.backend.guest.GuestRepository;
import pl.sdacademy.backend.hotel.Hotel;
import pl.sdacademy.backend.hotel.HotelRepository;
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
    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;
    private ReservationRepository reservationRepository;
    private GuestRepository guestRepository;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, HotelRepository hotelRepository, RoomRepository roomRepository, ReservationRepository reservationRepository, GuestRepository guestRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
    }

    @PostConstruct
    public void onInit() {
        User user1 = new User(
                "user1",
                passwordEncoder.encode("pass1"),
                "USER"
        );
        userRepository.save(user1);
        User user2 = new User(
                "user2",
                passwordEncoder.encode("pass2"),
                "USER"
        );
        userRepository.save(user2);
        User user3 = new User(
                "user3",
                passwordEncoder.encode("pass3"),
                "ADMIN"
        );
        userRepository.save(user3);

        Hotel hotel = new Hotel("ourOnlyHotel", "Poland");
        hotelRepository.save(hotel);

        Room room1 = new Room("101", hotel, true, 2, BigDecimal.valueOf(150.00));
        roomRepository.save(room1);
        Room room2 = new Room("102", hotel, false, 4, BigDecimal.valueOf(252.50));
        roomRepository.save(room2);

        Guest guest1 = new Guest("Pawel", "Buczek");
        guestRepository.save(guest1);
        Guest guest2 = new Guest("Wojciech", "Bassara");
        guestRepository.save(guest2);

        Reservation reservation1 = new Reservation(room1,
                guest1,
                LocalDate.of(2020,12,10),
                LocalDate.of(2020,12,10),
                false);
        reservationRepository.save(reservation1);

        Reservation reservation2 = new Reservation(room2,
                guest2,
                LocalDate.of(2020,12,25),
                LocalDate.of(2020,12,31),
                true);
        reservationRepository.save(reservation2);
    }
}

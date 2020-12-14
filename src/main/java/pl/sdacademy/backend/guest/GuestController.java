package pl.sdacademy.backend.guest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GuestController {
    GuestRepository guestRepository;

    public GuestController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping("/guest")
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @GetMapping("/guest/{id}")
    public Guest findById(@PathVariable Long id) throws NoSuchGuestException {
        return guestRepository.findById(id).orElseThrow(() -> new NoSuchGuestException("No Guest was found with ID: " + id));
    }

    @GetMapping("/guest/by-name/{firstName}+{lastName}")
    public Guest getByFirstAndLastName(@PathVariable String firstName,@PathVariable String lastName) throws NoSuchGuestException {
        return guestRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new NoSuchGuestException("No Guest was found with firstName: " + firstName
                        + " and lastName: " + lastName));
    }

    //public List<Room> getAvailableRoomsByDates(LocalDate startDate, LocalDate endDate)

    //public List<Room> getReservedRoomsByDates(LocalDate startDate, LocalDate endDate)

}

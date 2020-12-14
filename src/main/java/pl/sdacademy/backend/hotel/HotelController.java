package pl.sdacademy.backend.hotel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotelController {
    HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/hotel")
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @GetMapping("/hotel/{id}")
    public Hotel getById(@PathVariable Long id) throws NoSuchHotelException {
        return hotelRepository.findById(id).orElseThrow(() -> new NoSuchHotelException("No Hotel was found with ID: " + id));
    }

    @GetMapping("/hotel/by-name/{name}")
    public Hotel getByName(@PathVariable String name) throws NoSuchHotelException {
        return hotelRepository.findByName(name)
                .orElseThrow(() -> new NoSuchHotelException("No Hotel was found with name: " + name));
    }

}

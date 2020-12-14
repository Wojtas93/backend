package pl.sdacademy.backend.room;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {
    RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/room")
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @GetMapping("/room/{id}")
    public Room getById(@PathVariable Long id) throws NoSuchRoomException {
        return roomRepository.findById(id).orElseThrow(() -> new NoSuchRoomException("No Room was found with ID: " + id));
    }

    @GetMapping("/room/by-nr/{roomNr}")
    public Room getByNr(@PathVariable String roomNr) throws NoSuchRoomException {
        return roomRepository.findByRoomNr(roomNr)
                .orElseThrow(() -> new NoSuchRoomException("No Room was found with nr: " + roomNr));
    }

}

package pl.sdacademy.backend.room;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.backend.Errors.ResponseMessage;

@RestController
@RequestMapping("/room")
@ResponseStatus(HttpStatus.OK)
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public RoomResponseDto get() {
        return new RoomResponseDto(roomRepository.findAll());
    }

    @GetMapping("/{id}")
    public Room get(@PathVariable Long id) {
        return roomRepository.getOne(id);
    }

    @GetMapping("/{roomNr}")
    public Room get(@PathVariable String roomNr) {
        return roomRepository.findByRoomNr(roomNr).get();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseMessage update(@RequestBody Room roomNew, @PathVariable long id) {
        Room room = roomRepository.findById(roomNew.getId()).get();
        room.setRoomNr(roomNew.getRoomNr());
        room.setCleaned(roomNew.isCleaned());
        room.setNumPeople(roomNew.getNumPeople());
        room.setPrice(roomNew.getPrice());
        roomRepository.save(room);
        return new ResponseMessage("Room updated");
    }

    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable long id) throws NoSuchRoomException {
        roomRepository.delete(roomRepository.findById(id).get());
        return new ResponseMessage("room deleted");

    }
}

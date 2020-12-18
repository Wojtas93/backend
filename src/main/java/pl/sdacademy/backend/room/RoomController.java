package pl.sdacademy.backend.room;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.backend.Errors.ResponseMessage;

import java.util.Collections;

@RestController
@RequestMapping("/room")
public class RoomController {

   private RoomRepository roomRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public ResponseEntity<RoomResponseDto> get() throws NoSuchRoomException{
        try{
            return ResponseEntity.ok(new RoomResponseDto(roomRepository.findAll()));
        }catch (NoSuchRoomException e){
            LOGGER.info("DB is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RoomResponseDto(Collections.emptyList()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> get(@PathVariable Long id) throws NoSuchRoomException {
        try{
            return ResponseEntity.ok(roomRepository.getOne(id));
        }catch (NoSuchRoomException e){
            LOGGER.info("Couldn't find this room, check id");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{roomNr}")
    public ResponseEntity<Room> get(@PathVariable String roomNr) throws NoSuchRoomException {
        try{
            return ResponseEntity.ok(roomRepository.findByRoomNr(roomNr).get());
        }catch (NoSuchRoomException e){
            LOGGER.info("Couldn't find this room, check room Number");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> update(@RequestBody Room roomNew, @PathVariable long id) throws NoSuchRoomException{
        try {
            Room room = roomRepository.getOne(id);
            room.setRoomNr(roomNew.getRoomNr());
            room.setCleaned(roomNew.isCleaned());
            room.setNumPeople(roomNew.getNumPeople());
            room.setPrice(roomNew.getPrice());
            roomRepository.save(room);
            return ResponseEntity.ok(new ResponseMessage("Room updated"));
        } catch (NoSuchRoomException e) {
            LOGGER.info("Couldn't update this room");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Couldn't find this room"));
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable long id) throws NoSuchRoomException{
        try {
            roomRepository.delete(roomRepository.getOne(id));
            LOGGER.info("room deleted successful");
            return ResponseEntity.ok(new ResponseMessage("room deleted"));
        } catch (NoSuchRoomException e) {
            LOGGER.info("Couldn't delete this room");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Couldn't deleted this room"));
        }
    }
}

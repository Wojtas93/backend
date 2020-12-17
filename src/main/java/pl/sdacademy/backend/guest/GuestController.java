package pl.sdacademy.backend.guest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.backend.TextRespone.TextResponse;
import pl.sdacademy.backend.room.NoSuchRoomException;
import pl.sdacademy.backend.room.Room;
import pl.sdacademy.backend.room.RoomController;
import pl.sdacademy.backend.room.RoomResponseDto;

import java.util.Collections;
import java.util.List;

@RestController
public class GuestController {
    
    private GuestRepository guestRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(GuestController.class);

    public GuestController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }
    @GetMapping
    public ResponseEntity<GuestResponseDto> get() throws NoSuchGuestException {
        try{
            return ResponseEntity.ok(new GuestResponseDto(guestRepository.findAll()));
        }catch (NoSuchGuestException e){
            LOGGER.info("DB is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GuestResponseDto(Collections.emptyList()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> get(@PathVariable Long id) throws NoSuchGuestException {
        try{
            return ResponseEntity.ok(guestRepository.getOne(id));
        }catch (NoSuchGuestException e){
            LOGGER.info("Couldn't find this guest, check id");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{guestLastName}")
    public ResponseEntity<Guest> get(@PathVariable String guestLastName) throws NoSuchGuestException {
        try{
            return ResponseEntity.ok(guestRepository.findByLastName(guestLastName).get());
        }catch (NoSuchGuestException e){
            LOGGER.info("Couldn't find this guest, check guest Number");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/{guestFirstName}/{guestLastName}")
    public ResponseEntity<Guest> get(@PathVariable String guestFirstName,@PathVariable String guestLastName) throws NoSuchGuestException {
        try{
            return ResponseEntity.ok(guestRepository.findByFirstNameAndLastName(guestFirstName,guestLastName).get());
        }catch (NoSuchGuestException e){
            LOGGER.info("Couldn't find this guest, check guest Number");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<TextResponse> update(@RequestBody Guest guestNew, @PathVariable long id) throws NoSuchGuestException{
        try {
            Guest guest = guestRepository.getOne(id);
            guest.setFirstName(guestNew.getFirstName());
            guest.setLastName(guestNew.getLastName());
            guestRepository.save(guest);
            return ResponseEntity.ok(new TextResponse("Guest updated"));
        } catch (NoSuchGuestException e) {
            LOGGER.info("Couldn't update this guest");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TextResponse("Couldn't find this guest"));
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TextResponse> delete(@PathVariable long id) throws NoSuchGuestException{
        try {
            guestRepository.delete(guestRepository.getOne(id));
            LOGGER.info("guest deleted successful");
            return ResponseEntity.ok(new TextResponse("guest deleted"));
        } catch (NoSuchGuestException e) {
            LOGGER.info("Couldn't delete this guest");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TextResponse("Couldn't deleted this guest"));
        }
    }

}

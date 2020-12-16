package pl.sdacademy.backend.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> get() {
        try {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setUsers(userRepository.findAll());
            return ResponseEntity.ok(userResponseDto);
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't find this user");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't find this user",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable long id) {
        try {
            return ResponseEntity.ok(userRepository.getOne(id));
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't find this user");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't find this user",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> get(@PathVariable String userName) {
        try {
            return ResponseEntity.ok(Objects.requireNonNull(userRepository.findByUsername(userName).get()));
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't find this user");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't find this user",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<String> create(@RequestBody User user) {
        try {
            userRepository.save(user);
            return ResponseEntity.ok("User created");
        } catch (Exception e) {
            LOGGER.info("Couldn't create this user");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't create this user",
                    HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> update(@RequestBody User userNew, @PathVariable long id) {
        try {
            User user = userRepository.getOne(id);
            user.setUsername(userNew.getUsername());
            user.setPassword(userNew.getPassword());
            user.setRole(userNew.getRole());
            userRepository.save(user);
            return ResponseEntity.ok("User updated");
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't update this user");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't update this user",
                    HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        try {
            userRepository.delete(userRepository.getOne(id));
            LOGGER.info("user deleted successful");
            return ResponseEntity.ok("user deleted");
        } catch (Exception e) {
            LOGGER.info("Couldn't delete this user");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't deleted this user",
                    HttpStatus.BAD_REQUEST);
        }
    }

}

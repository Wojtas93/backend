package pl.sdacademy.backend.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository  userRepository;
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable long id) {
        try {
            return ResponseEntity.ok(userRepository.getOne(id));
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't find this user");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> get(@PathVariable String userName) {
        try {
            return ResponseEntity.ok(Objects.requireNonNull(userRepository.findByUsername(userName).get()));
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't find this user");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    @PostMapping("/post")
    public ResponseEntity<TextResponse> create(@RequestBody User user) {
        try {
            userRepository.save(user);
            return ResponseEntity.ok(new TextResponse("User created"));
        } catch (Exception e) {
            LOGGER.info("Couldn't create this user");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TextResponse("Couldn't create this user"));
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<TextResponse> update(@RequestBody User userNew, @PathVariable long id) {
        try {
            User user = userRepository.getOne(id);
            user.setUsername(userNew.getUsername());
            user.setPassword(userNew.getPassword());
            user.setRole(userNew.getRole());
            userRepository.save(user);
            return ResponseEntity.ok(new TextResponse("User updated"));
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't update this user");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TextResponse("Couldn't find this user"));
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TextResponse> delete(@PathVariable long id) {
        try {
            userRepository.delete(userRepository.getOne(id));
            LOGGER.info("user deleted successful");
            return ResponseEntity.ok(new TextResponse("user deleted"));
        } catch (Exception e) {
            LOGGER.info("Couldn't delete this user");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TextResponse("Couldn't deleted this user"));
        }
    }

}

package pl.sdacademy.backend.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.backend.Errors.ResponseMessage;

import java.util.List;


@RestController
@RequestMapping("/user")
@ResponseStatus(HttpStatus.OK)
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public UserResponseDto get() {
        return new UserResponseDto(userRepository.findAll());
    }

    @GetMapping("/admin")
    public List<User> getAdmin() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable long id) {
        return userRepository.findById(id).get();
    }

    @GetMapping("/{userName}/{password}")
    public User get(@PathVariable String userName, @PathVariable String password) {
        return userRepository.findByUsernameAndPassword(userName, passwordEncoder.encode(password)).get();
    }

//    @GetMapping("/{userLastName}")
//    public User get(@PathVariable String userLastName) {
//        return userRepository.findByLastName(userLastName).get();
//    }
//
//    @GetMapping("/{userFirstName}/{userLastName}")
//    public User get(@PathVariable String userFirstName, @PathVariable String userLastName) {
//        return userRepository.findByFirstNameAndLastName(userFirstName, userLastName).get();
//    }

    @PostMapping("/post")
    public ResponseMessage create(@Validated @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseMessage("User created");
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseMessage update(@RequestBody User userNew, @PathVariable long id) {
        User user = userRepository.getOne(id);
        user.setUsername(userNew.getUsername());
        user.setPassword(userNew.getPassword());
        user.setRole(userNew.getRole());
        user.setFirstName(userNew.getFirstName());
        user.setLastName(user.getLastName());
        userRepository.save(user);
        return new ResponseMessage("User updated");
    }

    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable long id) {
        userRepository.delete(userRepository.getOne(id));
        return new ResponseMessage("user deleted");

    }

}

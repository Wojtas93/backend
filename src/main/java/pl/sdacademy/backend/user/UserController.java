package pl.sdacademy.backend.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) throws NoSuchUserException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserException("User with ID: " + id + " not found."));
    }

    @GetMapping("/{userName}")
    public User getByName(@PathVariable String userName) throws NoSuchUserException {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new NoSuchUserException("User with name: " + userName + " not found."));
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public User edit(@RequestBody User sentUser, @PathVariable Long id) {

        Optional<User> userOp = userRepository.findById(id);
        User user = null;
        if (userOp.isPresent()) {
            user = userOp.get();

            user.setUsername(sentUser.getUsername());
            user.setPassword(sentUser.getPassword());
            user.setRole(sentUser.getRole());
            return userRepository.save(user);
        }
        return user;
    }
}

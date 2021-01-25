package pl.sdacademy.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<User> findByLastName(String lastName);

    Optional<User> findByUsernameAndPassword(String userName, String encode);

    Optional<User> findByEmail(String email);
}

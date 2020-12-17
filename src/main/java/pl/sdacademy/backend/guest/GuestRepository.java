package pl.sdacademy.backend.guest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    Optional<Guest> findByFirstNameAndLastName (String firstName, String lastName);
    Optional<Guest> findByLastName ( String lastName);
}

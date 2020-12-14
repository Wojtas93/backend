package pl.sdacademy.backend.guest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;


    public Guest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Guest() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(id, guest.id) && Objects.equals(firstName, guest.firstName) && Objects.equals(lastName, guest.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "Id=" + id +
                ", fName='" + firstName + '\'' +
                ", lName='" + lastName + '\'' +
                '}';
    }
}

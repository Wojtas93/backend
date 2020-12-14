package pl.sdacademy.backend.hotel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String address;

    public Hotel(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Hotel() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(Id, hotel.Id) && Objects.equals(name, hotel.name) && Objects.equals(address, hotel.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, address);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

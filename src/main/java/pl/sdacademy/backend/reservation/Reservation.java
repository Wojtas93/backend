package pl.sdacademy.backend.reservation;

import pl.sdacademy.backend.guest.Guest;
import pl.sdacademy.backend.room.Room;
import pl.sdacademy.backend.room.RoomRepository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;
    @ManyToOne(fetch = FetchType.EAGER)
    private Guest guest;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isPaid;


    public Reservation() {
    }

    public Reservation(Room room, Guest guest, LocalDate startDate, LocalDate endDate, Boolean isPaid) {
        this.room = room;
        this.guest = guest;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPaid = isPaid;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(Id, that.Id) && Objects.equals(room, that.room) && Objects.equals(guest, that.guest) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(isPaid, that.isPaid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, room, guest, startDate, endDate, isPaid);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "Id=" + Id +
                ", room=" + room +
                ", guest=" + guest +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isPaid=" + isPaid +
                '}';
    }
}

package pl.sdacademy.backend.reservation;


import org.springframework.format.annotation.DateTimeFormat;
import pl.sdacademy.backend.room.Room;
import pl.sdacademy.backend.user.User;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future
    private ZonedDateTime startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future
    private ZonedDateTime endDate;

    @NotNull
    private Boolean isPaid;

    public Reservation() {
    }

    public Reservation(Room room, User user, LocalDate startDate, LocalDate endDate, Boolean isPaid) {
        this.room = room;
        this.user = user;
        this.startDate = startDate.atStartOfDay(ZoneId.systemDefault());
        this.endDate = endDate.atStartOfDay(ZoneId.systemDefault());
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStartDate() {
        return startDate.toLocalDate();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate.atStartOfDay(ZoneId.systemDefault());
    }

    public LocalDate getEndDate() {
        return endDate.toLocalDate();
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate.atStartOfDay(ZoneId.systemDefault());
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
        return Objects.equals(Id, that.Id) && Objects.equals(room, that.room) && Objects.equals(user, that.user) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(isPaid, that.isPaid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, room, user, startDate, endDate, isPaid);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "Id=" + Id +
                ", room=" + room +
                ", guest=" + user +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isPaid=" + isPaid +
                '}';
    }
}

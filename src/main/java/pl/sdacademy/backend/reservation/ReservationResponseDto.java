package pl.sdacademy.backend.reservation;


import java.util.List;

public class ReservationResponseDto {
    private List<Reservation> reservations;

    public ReservationResponseDto(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}

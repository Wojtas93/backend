package pl.sdacademy.backend.guest;

import java.util.List;

public class GuestResponseDto {
    private List<Guest> guests;

    public GuestResponseDto(List<Guest> guests) {
        this.guests = guests;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }
}

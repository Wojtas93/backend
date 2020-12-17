package pl.sdacademy.backend.room;

import java.util.List;

public class RoomResponseDto {
    private List<Room> rooms;

    public RoomResponseDto(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}

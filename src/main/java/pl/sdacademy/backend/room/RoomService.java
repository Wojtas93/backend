package pl.sdacademy.backend.room;

public class RoomService {
    void cleanRoom(Room room) {
        room.setCleaned(true);
    }

    //like, after guest leave, our room is considered not clean
    void messRoom(Room room) {
        room.setCleaned(false);
    }

}

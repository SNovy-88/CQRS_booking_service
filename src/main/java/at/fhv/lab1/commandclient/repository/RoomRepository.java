package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomRepository implements IRoomRepository {
    private final List<Room> rooms = new ArrayList<>();

    public Boolean addRoom(Room room) {
        if (room == null) {
            return false;
        }
        rooms.add(room);
        return true;
    }

    public Boolean deleteAllRooms() {
        if (!rooms.isEmpty() && rooms != null) {
            rooms.clear();
            return true;
        } else {
            return false;
        }
    }
}

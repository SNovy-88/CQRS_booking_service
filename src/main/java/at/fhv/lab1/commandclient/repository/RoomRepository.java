package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomRepository implements IRoomRepository {
    private final List<Room> rooms = new ArrayList<>();

    @Override
    public Boolean addRoom(Room room) {
        if (room == null) {
            return false;
        }
        rooms.add(room);
        return true;
    }

    @Override
    public Boolean deleteAllRooms() {
        if (!rooms.isEmpty() && rooms != null) {
            rooms.clear();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Room getRoomById(long roomId) {
        for (Room room : rooms) {
            if (room.getId() == roomId) {
                return room;
            }
        }
        return null;
    }
}

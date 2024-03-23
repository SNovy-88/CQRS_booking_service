package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomRepository {

    private final List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Room added: " + room);
    }

    public List<Room> getAllRooms() {
        return rooms;
    }
}
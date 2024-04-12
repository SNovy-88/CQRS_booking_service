package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Room;

import java.util.List;

public interface IRoomRepository {
    Boolean addRoom(Room room);

    Boolean deleteAllRooms();

    Room getRoomById(long roomId);

    List<Room> getRooms();
}

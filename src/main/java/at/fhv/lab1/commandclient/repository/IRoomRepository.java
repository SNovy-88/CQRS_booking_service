package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Room;

public interface IRoomRepository {
    Boolean addRoom(Room room);

    Boolean deleteAllRooms();

    Room getRoomById(long roomId);
}

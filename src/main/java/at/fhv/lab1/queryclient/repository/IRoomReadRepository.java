package at.fhv.lab1.queryclient.repository;

import at.fhv.lab1.queryclient.projection.FreeRoom;

import java.time.LocalDate;
import java.util.List;

public interface IRoomReadRepository {
    Boolean addRoom(FreeRoom room);

    List<FreeRoom> getFreeRoomsByCapacity(LocalDate checkInDate, LocalDate checkOutDate, int numberOfPersons);

    List<FreeRoom> getRoomsByNumber(long roomNumber);

    boolean deleteQueryModels();

    List<FreeRoom> getAllFreeRooms();

    void deleteRoom(FreeRoom freeRoom2);
}

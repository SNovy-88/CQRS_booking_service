package at.fhv.lab1.queryclient.repository;

import at.fhv.lab1.queryclient.model.FreeRoom;

import java.time.LocalDate;
import java.util.List;

public interface IRoomReadRepository {
    void addRoom(FreeRoom room);

    List<FreeRoom> getFreeRoomsByCapacity(LocalDate checkInDate, LocalDate checkOutDate, int numberOfPersons);

    List<FreeRoom> getRoomsByNumber(long roomNumber);
}

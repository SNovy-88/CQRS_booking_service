package at.fhv.lab1.queryclient.repository;

import at.fhv.lab1.queryclient.model.FreeRoom;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomReadRepository {
    List<FreeRoom> rooms = new ArrayList<>();

    public void addRoom(FreeRoom room) {
        rooms.add(room);
    }

    public List<FreeRoom> getRoomsByNumber(long roomNumber) {
        return rooms.stream()
                .filter(room -> room.getRoomNumber() == roomNumber)
                .collect(Collectors.toList());
    }

    public List<FreeRoom> getFreeRoomsByCapacity(LocalDate checkInDate, LocalDate checkOutDate, int numberOfPersons) {
        return rooms.stream()
                .filter(room -> room.getFromDate().isBefore(checkOutDate) && room.getToDate().isAfter(checkInDate) && room.getCapacity() >= numberOfPersons)
                .collect(Collectors.toList());
    }
}

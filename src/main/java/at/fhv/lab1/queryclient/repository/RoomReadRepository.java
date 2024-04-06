package at.fhv.lab1.queryclient.repository;

import at.fhv.lab1.queryclient.model.FreeRoom;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomReadRepository implements IRoomReadRepository {
    List<FreeRoom> freeRooms = new ArrayList<>();

    @Override
    public Boolean addRoom(FreeRoom room) {
        freeRooms.add(room);

        return true;
    }

    @Override
    public List<FreeRoom> getRoomsByNumber(long roomNumber) {
        return freeRooms.stream()
                .filter(room -> room.getRoomNumber() == roomNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<FreeRoom> getFreeRoomsByCapacity(LocalDate checkInDate, LocalDate checkOutDate, int numberOfPersons) {
        List<FreeRoom> freeRooms = new ArrayList<>();
        for (FreeRoom room : this.freeRooms) {
            if (room.getFromDate().isBefore(checkOutDate) && room.getToDate().isAfter(checkInDate) && room.getCapacity() >= numberOfPersons) {
                freeRooms.add(room);
            }
        }
        return freeRooms;
    }

    @Override
    public Boolean deleteQueryModels() {
        if (freeRooms.isEmpty() && freeRooms != null) {
            freeRooms.clear();

            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<FreeRoom> getAllFreeRooms() {
        return freeRooms;
    }

    @Override
    public void deleteRoom(FreeRoom freeRoom2) {
        freeRooms.remove(freeRoom2);
    }
}

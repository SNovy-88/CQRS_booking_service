package at.fhv.lab1.queryclient.repository;

import at.fhv.lab1.commandclient.model.Room;
import at.fhv.lab1.queryclient.model.FreeRoom;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomReadRepository implements IRoomReadRepository {
    List<FreeRoom> rooms = new ArrayList<>();

    @Override
    public void addRoom(FreeRoom room) {
        rooms.add(room);
        for(FreeRoom r: rooms){
            System.out.println(r.getRoomNumber());
            System.out.println(r.getFromDate());
        }


    }

    @Override
    public List<FreeRoom> getRoomsByNumber(long roomNumber) {
        return rooms.stream()
                .filter(room -> room.getRoomNumber() == roomNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<FreeRoom> getFreeRoomsByCapacity(LocalDate checkInDate, LocalDate checkOutDate, int numberOfPersons) {
        List<FreeRoom> freeRooms = new ArrayList<>();
        for (FreeRoom room : rooms) {
            if (room.getFromDate().isBefore(checkOutDate) && room.getToDate().isAfter(checkInDate) && room.getCapacity() >= numberOfPersons) {
                freeRooms.add(room);
                System.out.println("Room: " + room.getRoomNumber());
            }
        }
        return freeRooms;
    }
}

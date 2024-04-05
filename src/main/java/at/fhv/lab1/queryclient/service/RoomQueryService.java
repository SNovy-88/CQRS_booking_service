package at.fhv.lab1.queryclient.service;

import at.fhv.lab1.commandclient.model.Room;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.queryclient.model.FreeRoom;
import at.fhv.lab1.queryclient.repository.RoomReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomQueryService {
    private final RoomReadRepository roomRepository;

    @Autowired
    public RoomQueryService(RoomReadRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void addRoomToRepository(Room room) {
        int roomNumber = room.getRoomNumber();
        int capacity = room.getCapacity();

        FreeRoom freeRoom = new FreeRoom(roomNumber, LocalDate.of(1900, 01, 01), LocalDate.of(2100, 12, 31), capacity);

        roomRepository.addRoom(freeRoom);
    }

    public List<FreeRoom> getFreeRooms(LocalDate checkInDate, LocalDate checkOutDate, int numberOfPersons) {
        List<FreeRoom> freeRooms = roomRepository.getFreeRoomsByCapacity(checkInDate, checkOutDate, numberOfPersons);
        return freeRooms;
    }

    public void updateFreeRoomsFromEvent(RoomBookedEvent event) {
        System.out.println("Updating rooms from event: " + event);

        List<FreeRoom> rooms = roomRepository.getRoomsByNumber(event.getRoom().getRoomNumber());

        for (FreeRoom room : rooms) {
            LocalDate newToDate = LocalDate.of(2100, 12, 31);
            if (event.getCheckInDate().isBefore(room.getToDate()) && event.getCheckOutDate().isAfter(room.getFromDate())) {
                newToDate = room.getToDate();
                room.setToDate(event.getCheckInDate());
            }
            roomRepository.addRoom(new FreeRoom(event.getRoom().getRoomNumber(), event.getCheckOutDate(), newToDate, event.getRoom().getCapacity()));
        }
    }
}

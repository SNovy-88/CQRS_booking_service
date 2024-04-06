package at.fhv.lab1.queryclient.service;

import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.events.RoomCreatedEvent;
import at.fhv.lab1.queryclient.model.FreeRoom;
import at.fhv.lab1.queryclient.repository.RoomReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomQueryService implements IRoomQueryService {
    private final RoomReadRepository roomRepository;

    @Autowired
    public RoomQueryService(RoomReadRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Boolean addRoomToRepository(RoomCreatedEvent event) {
        int roomNumber = event.getRoom().getRoomNumber();
        int capacity = event.getRoom().getCapacity();

        FreeRoom freeRoom = new FreeRoom(roomNumber, LocalDate.of(1900, 01, 01), LocalDate.of(2100, 12, 31), capacity);

        return roomRepository.addRoom(freeRoom);
    }

    @Override
    public List<FreeRoom> getFreeRooms(LocalDate checkInDate, LocalDate checkOutDate, int numberOfPersons) {
        List<FreeRoom> freeRooms = roomRepository.getFreeRoomsByCapacity(checkInDate, checkOutDate, numberOfPersons);
        return freeRooms;
    }

    @Override
    public Boolean bookRoom(RoomBookedEvent event) {
        List<FreeRoom> rooms = roomRepository.getRoomsByNumber(event.getBooking().getRoom().getRoomNumber());
        Boolean success = false;

        for (FreeRoom room : rooms) {
            LocalDate newToDate = LocalDate.of(2100, 12, 31);
            if (event.getBooking().getCheckInDate().isBefore(room.getToDate()) && event.getBooking().getCheckOutDate().isAfter(room.getFromDate())) {
                newToDate = room.getToDate();
                room.setToDate(event.getBooking().getCheckInDate());
            }
            success = roomRepository.addRoom(new FreeRoom(event.getBooking().getRoom().getRoomNumber(), event.getBooking().getCheckOutDate(), newToDate, event.getBooking().getRoom().getCapacity()));
        }
        return success;
    }

    @Override
    public Boolean deleteQueryModels() {
        return roomRepository.deleteQueryModels();
    }
}

package at.fhv.lab1.queryclient.service;

import at.fhv.lab1.eventbus.events.BookingCanceledEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.events.RoomCreatedEvent;
import at.fhv.lab1.queryclient.projection.FreeRoom;
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
        int roomNumber = event.getRoom().getId();
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
        List<FreeRoom> rooms = roomRepository.getRoomsByNumber(event.getBooking().getRoom().getId());
        Boolean success = false;

        for (FreeRoom room : rooms) {
            if ((event.getBooking().getCheckInDate().isAfter(room.getFromDate()) ||
                    event.getBooking().getCheckInDate().isEqual(room.getFromDate())) &&
                    (event.getBooking().getCheckOutDate().isBefore(room.getToDate()) ||
                            event.getBooking().getCheckOutDate().isEqual(room.getToDate()))) {
                if ((room.getToDate().isEqual(LocalDate.of(2100, 12, 31)) &&
                        (!room.getFromDate().isEqual(LocalDate.of(1900, 01, 01))))) {
                    room.setToDate(event.getBooking().getCheckInDate());
                    event.getBooking().setCheckInDate(event.getBooking().getCheckOutDate());
                    event.getBooking().setCheckOutDate(LocalDate.of(2100, 12, 31));
                } else if ((room.getFromDate().isEqual(LocalDate.of(1900, 01, 01))) &&
                        (!room.getToDate().isEqual(LocalDate.of(2100, 12, 31)))) {
                    room.setFromDate(event.getBooking().getCheckOutDate());
                    event.getBooking().setCheckOutDate(event.getBooking().getCheckInDate());
                    event.getBooking().setCheckInDate(LocalDate.of(1900, 01, 01));
                } else if ((room.getFromDate().isEqual(LocalDate.of(1900, 01, 01))) &&
                        (room.getToDate().isEqual(LocalDate.of(2100, 12, 31)))) {
                    room.setToDate(event.getBooking().getCheckInDate());
                    event.getBooking().setCheckInDate(event.getBooking().getCheckOutDate());
                    event.getBooking().setCheckOutDate(LocalDate.of(2100, 12, 31));
                } else {
                    LocalDate newToDate = room.getToDate();
                    room.setToDate(event.getBooking().getCheckInDate());
                    event.getBooking().setCheckOutDate(newToDate);
                }
                success = roomRepository.addRoom(new FreeRoom(event.getBooking().getRoom().getId(), event.getBooking().getCheckInDate(), event.getBooking().getCheckOutDate(), event.getBooking().getRoom().getCapacity()));
            }
        }
        return success;
    }

    @Override
    public Boolean cancelBooking(BookingCanceledEvent event) {
        List<FreeRoom> rooms = roomRepository.getRoomsByNumber(event.getBooking().getRoom().getId());
        FreeRoom freeRoom1 = null;
        FreeRoom freeRoom2 = null;

        for (FreeRoom room : rooms) {
            if (room.getToDate().isEqual(event.getBooking().getCheckInDate())) {
                freeRoom1 = room;
            }
            if (room.getFromDate().isEqual(event.getBooking().getCheckOutDate())) {
                freeRoom2 = room;
            }
        }
        if (freeRoom1 != null && freeRoom2 != null) {
            for (FreeRoom room : rooms) {
                if (room.equals(freeRoom1)) {
                    room.setToDate(freeRoom2.getToDate());
                    roomRepository.deleteRoom(freeRoom2);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteQueryModels() {
        return roomRepository.deleteQueryModels();
    }

    @Override
    public List<FreeRoom> getAllFreeRooms() {
        return roomRepository.getAllFreeRooms();
    }
}

package at.fhv.lab1.queryclient.service;

import at.fhv.lab1.eventbus.events.BookingCanceledEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.events.RoomCreatedEvent;
import at.fhv.lab1.queryclient.model.FreeRoom;

import java.time.LocalDate;
import java.util.List;

public interface IRoomQueryService {
    Boolean addRoomToRepository(RoomCreatedEvent event);

    List<FreeRoom> getFreeRooms(LocalDate checkInDate, LocalDate checkOutDate, int numberOfPersons);

    Boolean bookRoom(RoomBookedEvent event);

    Boolean deleteQueryModels();

    List<FreeRoom> getAllFreeRooms();

    Boolean cancelBooking(BookingCanceledEvent event);
}

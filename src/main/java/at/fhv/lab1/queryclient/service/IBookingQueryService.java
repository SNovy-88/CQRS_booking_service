package at.fhv.lab1.queryclient.service;

import at.fhv.lab1.eventbus.events.BookingCanceledEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.queryclient.model.BookingProjection;

import java.time.LocalDate;
import java.util.List;

public interface IBookingQueryService {

    Boolean addBookingToRepository(RoomBookedEvent event);

    Boolean deleteQueryModels();

    List<BookingProjection> getBookingsByDate(LocalDate fromDate, LocalDate toDate);

    Boolean cancelBooking(BookingCanceledEvent event);
}

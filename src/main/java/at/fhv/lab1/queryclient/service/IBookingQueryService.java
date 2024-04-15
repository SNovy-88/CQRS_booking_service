package at.fhv.lab1.queryclient.service;

import at.fhv.lab1.eventbus.events.BookingCanceledEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.queryclient.projection.BookingProjection;

import java.time.LocalDate;
import java.util.List;

public interface IBookingQueryService {

    Boolean addBookingToRepository(RoomBookedEvent event);

    boolean deleteQueryModels();

    List<BookingProjection> getBookingsByDate(LocalDate fromDate, LocalDate toDate);

    List<BookingProjection> getAllBookings();

    Boolean cancelBooking(BookingCanceledEvent event);
}

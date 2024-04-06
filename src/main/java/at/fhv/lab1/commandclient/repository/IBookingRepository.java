package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Booking;

import java.util.List;

public interface IBookingRepository {
    Boolean addBooking(Booking booking);

    List<Booking> getAllBookings();

    Boolean deleteAllBookings();
}

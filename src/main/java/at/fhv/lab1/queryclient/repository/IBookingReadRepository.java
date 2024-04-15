package at.fhv.lab1.queryclient.repository;

import at.fhv.lab1.queryclient.projection.BookingProjection;

import java.util.List;

public interface IBookingReadRepository {
    Boolean addBooking(BookingProjection bookingProjection);

    List<BookingProjection> getAllBookings();

    boolean deleteQueryModels();

    Boolean cancelBooking(long bookingID);
}

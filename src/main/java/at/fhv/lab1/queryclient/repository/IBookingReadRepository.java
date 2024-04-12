package at.fhv.lab1.queryclient.repository;

import at.fhv.lab1.queryclient.projection.BookingProjection;

public interface IBookingReadRepository {
    Boolean addBooking(BookingProjection bookingProjection);

    boolean deleteQueryModels();

    Boolean cancelBooking(long bookingID);
}

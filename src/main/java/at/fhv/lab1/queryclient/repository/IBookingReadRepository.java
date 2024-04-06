package at.fhv.lab1.queryclient.repository;

import at.fhv.lab1.queryclient.model.BookingProjection;

public interface IBookingReadRepository {
    Boolean addBooking(BookingProjection bookingProjection);

    Boolean deleteQueryModels();

    Boolean cancelBooking(long bookingID);
}

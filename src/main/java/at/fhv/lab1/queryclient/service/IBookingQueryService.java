package at.fhv.lab1.queryclient.service;

import at.fhv.lab1.queryclient.model.BookingProjection;

import java.time.LocalDate;
import java.util.List;

public interface IBookingQueryService {

    Boolean addBookingToRepository(BookingProjection bookingProjection);

    Boolean deleteQueryModels();

    List<BookingProjection> getBookingsByDate(LocalDate fromDate, LocalDate toDate);
}

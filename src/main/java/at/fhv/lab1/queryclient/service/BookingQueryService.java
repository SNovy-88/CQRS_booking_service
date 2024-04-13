package at.fhv.lab1.queryclient.service;

import at.fhv.lab1.eventbus.events.BookingCanceledEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.queryclient.projection.BookingProjection;
import at.fhv.lab1.queryclient.repository.BookingReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingQueryService implements IBookingQueryService {
    private final BookingReadRepository bookingReadRepository;

    @Autowired
    public BookingQueryService(BookingReadRepository bookingReadRepository) {
        this.bookingReadRepository = bookingReadRepository;
    }

    @Override
    public Boolean addBookingToRepository(RoomBookedEvent event) {
        BookingProjection bookingProjection = new BookingProjection(event.getBooking().getBookingID(), event.getBooking().getCustomer().getCustomerID(), event.getBooking().getRoom().getId(), event.getBooking().getCheckInDate(), event.getBooking().getCheckOutDate());
        return bookingReadRepository.addBooking(bookingProjection);
    }

    @Override
    public boolean deleteQueryModels() {
        return bookingReadRepository.deleteQueryModels();
    }

    @Override
    public List<BookingProjection> getBookingsByDate(LocalDate fromDate, LocalDate toDate) {
        return bookingReadRepository.getBookingsByDate(fromDate, toDate);
    }

    @Override
    public List<BookingProjection> getAllBookings() {
        return bookingReadRepository.getAllBookings();
    }

    @Override
    public Boolean cancelBooking(BookingCanceledEvent event) {
        return bookingReadRepository.cancelBooking(event.getBooking().getBookingID());
    }
}

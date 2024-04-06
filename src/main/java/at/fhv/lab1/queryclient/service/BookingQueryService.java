package at.fhv.lab1.queryclient.service;

import at.fhv.lab1.queryclient.model.BookingProjection;
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
    public Boolean addBookingToRepository(BookingProjection bookingProjection) {
        return bookingReadRepository.addBooking(bookingProjection);
    }

    @Override
    public Boolean deleteQueryModels() {
        return bookingReadRepository.deleteQueryModels();
    }

    @Override
    public List<BookingProjection> getBookingsByDate(LocalDate fromDate, LocalDate toDate) {
        return bookingReadRepository.getBookingsByDate(fromDate, toDate);
    }
}
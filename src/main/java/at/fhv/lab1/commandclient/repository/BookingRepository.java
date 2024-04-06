package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Booking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingRepository implements IBookingRepository {

    private final List<Booking> bookings = new ArrayList<>();

    @Override
    public Boolean addBooking(Booking booking) {
        bookings.add(booking);

        return true;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookings;
    }

    @Override
    public Boolean deleteAllBookings() {
        if (!bookings.isEmpty() && bookings != null) {
            bookings.clear();
            return true;
        } else {
            return false;
        }
    }
}

package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Booking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingRepository {

    private final List<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
        System.out.println("Booking added: " + booking);
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }
}

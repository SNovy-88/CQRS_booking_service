package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Booking;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Booking> getAllBookingsByDate(LocalDate checkInDate, LocalDate checkOutDate) {
        return bookings.stream()
                .filter(e -> e.getCheckInDate().isBefore(checkInDate))
                .filter(e -> e.getCheckOutDate().isAfter(checkOutDate))
                .collect(Collectors.toList());
    }
}

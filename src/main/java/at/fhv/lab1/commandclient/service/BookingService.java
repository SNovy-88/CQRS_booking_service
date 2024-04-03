package at.fhv.lab1.commandclient.service;

import at.fhv.lab1.eventbus.EventPublisher;
import at.fhv.lab1.commandclient.model.Booking;
import at.fhv.lab1.commandclient.model.BookingRequest;
import at.fhv.lab1.commandclient.model.Customer;
import at.fhv.lab1.commandclient.model.Room;
import at.fhv.lab1.commandclient.repository.BookingRepository;
import at.fhv.lab1.eventbus.events.Event;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingService {
    private final BookingRepository repository;
    private final EventPublisher eventPublisher;

    @Autowired
    public BookingService(EventPublisher eventPublisher, BookingRepository repository) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public boolean bookRoom(BookingRequest bookingRequest) {
        if (validateBookingRequest(bookingRequest)) {
            Booking booking = createBooking(bookingRequest);
            repository.addBooking(booking);
            long duration = booking.getCheckOutDate().toEpochDay() - booking.getCheckInDate().toEpochDay();

            RoomBookedEvent event = new RoomBookedEvent(booking.getBookingID(), booking.getCustomer(), booking.getRoom().getRoomNumber(), duration);
            return eventPublisher.publishEvent(event);
        }
        return false;
    }

    private boolean validateBookingRequest(BookingRequest bookingRequest) {
        // TODO: Implement validation
        if (bookingRequest.getCustomerID() <= 0 || bookingRequest.getRoomID() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    private Booking createBooking(BookingRequest bookingRequest) {
        Booking booking = new Booking();
        booking.setCustomer(new Customer(1, "Lea", "Bildstein", null));
        booking.setRoom(new Room(1, 1, true));
        booking.setCheckInDate(bookingRequest.getCheckInDate());
        booking.setCheckOutDate(bookingRequest.getCheckOutDate());

        return booking;
    }


}

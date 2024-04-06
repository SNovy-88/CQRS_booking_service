package at.fhv.lab1.commandclient.service;

import at.fhv.lab1.commandclient.EventPublisher;
import at.fhv.lab1.commandclient.command.BookRoomCommand;
import at.fhv.lab1.commandclient.model.Booking;
import at.fhv.lab1.commandclient.model.Customer;
import at.fhv.lab1.commandclient.model.Room;
import at.fhv.lab1.commandclient.repository.BookingRepository;
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
    public boolean bookRoom(BookRoomCommand bookRoomCommand) {
        if (validateBookingRequest(bookRoomCommand)) {
            Booking booking = createBooking(bookRoomCommand);
            repository.addBooking(booking);

            RoomBookedEvent event = new RoomBookedEvent(booking);
            return eventPublisher.publishEvent(event);
        }
        return false;
    }

    private boolean validateBookingRequest(BookRoomCommand bookRoomCommand) {
        // TODO: Implement validation
        return bookRoomCommand.getCustomerID() > 0 && bookRoomCommand.getRoomID() > 0;
    }

    private Booking createBooking(BookRoomCommand bookRoomCommand) {
        Booking booking = new Booking();
        booking.setCustomer(new Customer("Lea", "Bildstein", null));
        booking.setRoom(new Room(1, 1, true));
        booking.setCheckInDate(bookRoomCommand.getCheckInDate());
        booking.setCheckOutDate(bookRoomCommand.getCheckOutDate());

        return booking;
    }


}

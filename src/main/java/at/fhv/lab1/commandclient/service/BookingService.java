package at.fhv.lab1.commandclient.service;

import at.fhv.lab1.commandclient.EventPublisher;
import at.fhv.lab1.commandclient.command.BookRoomCommand;
import at.fhv.lab1.commandclient.command.CancelBookingCommand;
import at.fhv.lab1.commandclient.model.Booking;
import at.fhv.lab1.commandclient.model.Customer;
import at.fhv.lab1.commandclient.model.Room;
import at.fhv.lab1.commandclient.repository.BookingRepository;
import at.fhv.lab1.commandclient.repository.CustomerRepository;
import at.fhv.lab1.commandclient.repository.RoomRepository;
import at.fhv.lab1.eventbus.events.BookingCanceledEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final EventPublisher eventPublisher;

    @Autowired
    public BookingService(BookingRepository bookingRepository, CustomerRepository customerRepository, RoomRepository roomRepository, EventPublisher eventPublisher) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Boolean bookRoom(BookRoomCommand bookRoomCommand) {
        Customer customer = customerRepository.getCustomerById(bookRoomCommand.getCustomerID());
        Room room = roomRepository.getRoomById(bookRoomCommand.getRoomID());

        Booking booking = new Booking(customer, room, bookRoomCommand.getCheckInDate(), bookRoomCommand.getCheckOutDate());
        if (bookingRepository.addBooking(booking)) {
            RoomBookedEvent event = new RoomBookedEvent(booking);
            return eventPublisher.publishEvent(event);
        } else {
            return false;
        }
    }

    @Override
    public boolean cancelBooking(CancelBookingCommand cancelBookingCommand) {
        Booking booking = bookingRepository.getBookingById(cancelBookingCommand.getBookingID());
        if (booking != null) {
            if (bookingRepository.cancelBooking(booking)) {
                BookingCanceledEvent event = new BookingCanceledEvent(booking);
                return eventPublisher.publishEvent(event);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

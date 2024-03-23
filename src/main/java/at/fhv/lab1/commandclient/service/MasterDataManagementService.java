package at.fhv.lab1.commandclient.service;

import at.fhv.lab1.commandclient.EventPublisher;
import at.fhv.lab1.commandclient.model.Booking;
import at.fhv.lab1.commandclient.model.Customer;
import at.fhv.lab1.commandclient.model.Room;
import at.fhv.lab1.commandclient.repository.BookingRepository;
import at.fhv.lab1.commandclient.repository.CustomerRepository;
import at.fhv.lab1.commandclient.repository.RoomRepository;
import at.fhv.lab1.eventbus.events.Event;
import at.fhv.lab1.eventbus.events.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MasterDataManagementService implements IMasterDataManagementService {
    private final EventPublisher eventPublisher;
    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public MasterDataManagementService(EventPublisher eventPublisher, RoomRepository roomRepository, CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.eventPublisher = eventPublisher;
        this.roomRepository = roomRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void restoreFromEvents() {
        // Logik zur Wiederherstellung des Zustands aus Events im Event-Store
    }

    @Override
    public void createCommandAndQueryModels() {
        // Initialize customers
        Customer customer1 = new Customer(1, "John Doe", "Main Street 123", LocalDate.of(1990, 5, 15));
        Customer customer2 = new Customer(2, "Jane Smith", "Broadway 456", LocalDate.of(1985, 8, 20));

        customerRepository.addCustomer(customer1);
        Event customerEvent1 = createCustomerEvent(customer1);
        eventPublisher.publishEvent(customerEvent1);
        customerRepository.addCustomer(customer2);
        Event customerEvent2 = createCustomerEvent(customer2);
        eventPublisher.publishEvent(customerEvent2);

        // Initialize rooms
        Room room1 = new Room(101, 2, true);
        Room room2 = new Room(102, 4, false);
        Room room3 = new Room(103, 1, true);
        roomRepository.addRoom(room1);
        Event roomEvent1 = createRoomEvent(room1);
        eventPublisher.publishEvent(roomEvent1);
        roomRepository.addRoom(room2);
        Event roomEvent2 = createRoomEvent(room2);
        eventPublisher.publishEvent(roomEvent2);
        roomRepository.addRoom(room3);
        Event roomEvent3 = createRoomEvent(room3);
        eventPublisher.publishEvent(roomEvent3);

        // Initialize bookings
        Booking booking1 = new Booking(customer1, room1, LocalDate.of(2024, 3, 25), LocalDate.of(2024, 3, 28));
        Booking booking2 = new Booking(customer2, room2, LocalDate.of(2024, 4, 10), LocalDate.of(2024, 4, 15));
        Booking booking3 = new Booking(customer1, room3, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 3));
        bookingRepository.addBooking(booking1);
        Event bookingEvent1 = createBookingEvent(booking1);
        eventPublisher.publishEvent(bookingEvent1);
        bookingRepository.addBooking(booking2);
        Event bookingEvent2 = createBookingEvent(booking2);
        eventPublisher.publishEvent(bookingEvent2);
        bookingRepository.addBooking(booking3);
        Event bookingEvent3 = createBookingEvent(booking3);
        eventPublisher.publishEvent(bookingEvent3);
    }

    private Event createCustomerEvent(Customer customer) {
        Event event = new Event();
        event.setType(EventType.MASTERDATA_CUSTOMER_INITIALIZED);
        event.setCustomer(customer.getCustomerID());
        event.setTimestamp(System.currentTimeMillis());
        event.setContent("Customer " + customer.getName() + " with ID" + customer.getCustomerID() + " initialized");

        return event;
    }

    private Event createRoomEvent(Room room) {
        Event event = new Event();
        event.setType(EventType.MASTERDATA_ROOM_INITIALIZED);
        event.setTimestamp(System.currentTimeMillis());
        event.setContent("Room " + room.getRoomNumber() + " initialized");

        return event;
    }

    private Event createBookingEvent(Booking booking) {
        Event event = new Event();
        event.setType(EventType.MASTERDATA_BOOKING_INITIALIZED);
        event.setCustomer(booking.getCustomer().getCustomerID());
        event.setTimestamp(System.currentTimeMillis());
        event.setContent("Booking for room " + booking.getRoom().getRoomNumber() + " from " + booking.getCheckInDate() + " to " + booking.getCheckOutDate() + " initialized");

        return event;
    }
}
package at.fhv.lab1.commandclient.service;

import at.fhv.lab1.eventbus.EventPublisher;
import at.fhv.lab1.commandclient.model.Booking;
import at.fhv.lab1.commandclient.model.Customer;
import at.fhv.lab1.commandclient.model.Room;
import at.fhv.lab1.commandclient.repository.BookingRepository;
import at.fhv.lab1.commandclient.repository.CustomerRepository;
import at.fhv.lab1.commandclient.repository.RoomRepository;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.Event;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.events.RoomCreatedEvent;
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
        CustomerCreatedEvent customerEvent1 = new CustomerCreatedEvent(customer1);
        eventPublisher.publishEvent(customerEvent1);
        customerRepository.addCustomer(customer2);
        CustomerCreatedEvent customerEvent2 = new CustomerCreatedEvent(customer2);
        eventPublisher.publishEvent(customerEvent2);

        // Initialize rooms
        Room room1 = new Room(101, 2, true);
        Room room2 = new Room(102, 4, false);
        Room room3 = new Room(103, 1, true);
        roomRepository.addRoom(room1);
        RoomCreatedEvent roomEvent1 = new RoomCreatedEvent(room1);
        eventPublisher.publishEvent(roomEvent1);
        roomRepository.addRoom(room2);
        RoomCreatedEvent roomEvent2 = new RoomCreatedEvent(room2);
        eventPublisher.publishEvent(roomEvent2);
        roomRepository.addRoom(room3);
        RoomCreatedEvent roomEvent3 = new RoomCreatedEvent(room3);
        eventPublisher.publishEvent(roomEvent3);

        // Initialize bookings
        Booking booking1 = new Booking(customer1, room1, LocalDate.of(2024, 3, 25), LocalDate.of(2024, 3, 28));
        Booking booking2 = new Booking(customer2, room2, LocalDate.of(2024, 4, 10), LocalDate.of(2024, 4, 15));
        Booking booking3 = new Booking(customer1, room3, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 3));
        bookingRepository.addBooking(booking1);
        RoomBookedEvent bookingEvent1 = new RoomBookedEvent(booking1);
        eventPublisher.publishEvent(bookingEvent1);
        bookingRepository.addBooking(booking2);
        RoomBookedEvent bookingEvent2 = new RoomBookedEvent(booking2);
        eventPublisher.publishEvent(bookingEvent2);
        bookingRepository.addBooking(booking3);
        RoomBookedEvent bookingEvent3 = new RoomBookedEvent(booking3);
        eventPublisher.publishEvent(bookingEvent3);
    }
}
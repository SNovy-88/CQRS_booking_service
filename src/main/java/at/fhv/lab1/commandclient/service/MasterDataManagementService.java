package at.fhv.lab1.commandclient.service;

import at.fhv.lab1.commandclient.EventPublisher;
import at.fhv.lab1.commandclient.model.Booking;
import at.fhv.lab1.commandclient.model.Customer;
import at.fhv.lab1.commandclient.model.Room;
import at.fhv.lab1.commandclient.repository.BookingRepository;
import at.fhv.lab1.commandclient.repository.CustomerRepository;
import at.fhv.lab1.commandclient.repository.RoomRepository;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.QueryModelsDeletedEvent;
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
    public Boolean createCommandAndQueryModels() {
        Room room1 = new Room(1, 2, true);
        roomRepository.addRoom(room1);
        eventPublisher.publishEvent(new RoomCreatedEvent(room1));

        Room room2 = new Room(2, 4, false);
        roomRepository.addRoom(room2);
        eventPublisher.publishEvent(new RoomCreatedEvent(room2));

        Room room3 = new Room(3, 4, true);
        roomRepository.addRoom(room3);
        eventPublisher.publishEvent(new RoomCreatedEvent(room3));

        Room room4 = new Room(4, 2, false);
        roomRepository.addRoom(room4);
        eventPublisher.publishEvent(new RoomCreatedEvent(room4));

        Room room5 = new Room(5, 6, false);
        roomRepository.addRoom(room5);
        eventPublisher.publishEvent(new RoomCreatedEvent(room5));


        Customer customer1 = new Customer("John Doe", "123 Main St, Anytown, USA", LocalDate.of(1990, 5, 15));
        customerRepository.addCustomer(customer1);
        eventPublisher.publishEvent(new CustomerCreatedEvent(customer1));

        Customer customer2 = new Customer("Alice Smith", "456 Elm St, Springfield, USA", LocalDate.of(1985, 8, 25));
        customerRepository.addCustomer(customer2);
        eventPublisher.publishEvent(new CustomerCreatedEvent(customer2));

        Customer customer3 = new Customer("Bob Johnson", "789 Oak St, Metropolis, USA", LocalDate.of(1975, 3, 10));
        customerRepository.addCustomer(customer3);
        eventPublisher.publishEvent(new CustomerCreatedEvent(customer3));


        Booking booking1 = new Booking(customer1, room1, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 5));
        bookingRepository.addBooking(booking1);
        eventPublisher.publishEvent(new RoomBookedEvent(booking1));

        Booking booking2 = new Booking(customer2, room2, LocalDate.of(2024, 5, 12), LocalDate.of(2024, 5, 20));
        bookingRepository.addBooking(booking2);
        eventPublisher.publishEvent(new RoomBookedEvent(booking2));

        Booking booking3 = new Booking(customer3, room3, LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 10));
        bookingRepository.addBooking(booking3);
        eventPublisher.publishEvent(new RoomBookedEvent(booking3));

        Booking booking4 = new Booking(customer1, room4, LocalDate.of(2024, 6, 22), LocalDate.of(2024, 6, 30));
        bookingRepository.addBooking(booking4);
        eventPublisher.publishEvent(new RoomBookedEvent(booking4));

        Booking booking5 = new Booking(customer2, room5, LocalDate.of(2024, 7, 15), LocalDate.of(2024, 7, 20));
        bookingRepository.addBooking(booking5);
        eventPublisher.publishEvent(new RoomBookedEvent(booking5));

        System.out.println("booking Repository: in MasterDataManagementService after adding Bookings:");

        return true;
    }
}
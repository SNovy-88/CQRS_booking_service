package at.fhv.lab1.commandclient.service;

import at.fhv.lab1.eventbus.EventPublisher;
import at.fhv.lab1.commandclient.model.Booking;
import at.fhv.lab1.commandclient.model.Customer;
import at.fhv.lab1.commandclient.model.Room;
import at.fhv.lab1.commandclient.repository.BookingRepository;
import at.fhv.lab1.commandclient.repository.CustomerRepository;
import at.fhv.lab1.commandclient.repository.RoomRepository;
import at.fhv.lab1.eventbus.events.*;
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
        roomRepository.addRoom(new Room(1, 2, true)); // Zimmer mit Zimmernummer 1, Kapazität 2 Personen, Meerblick
        roomRepository.addRoom(new Room(2, 4, false)); // Zimmer mit Zimmernummer 2, Kapazität 4 Personen, kein Meerblick
        roomRepository.addRoom(new Room(3, 4, true)); // Zimmer mit Zimmernummer 2, Kapazität 4 Personen, kein Meerblick
        roomRepository.addRoom(new Room(4, 2, false)); // Zimmer mit Zimmernummer 2, Kapazität 4 Personen, kein Meerblick
        roomRepository.addRoom(new Room(5, 6, false)); // Zimmer mit Zimmernummer 2, Kapazität 4 Personen, kein Meerblick
    }
}
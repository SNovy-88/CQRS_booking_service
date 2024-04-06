package at.fhv.lab1.eventbus.repository;

import at.fhv.lab1.eventbus.events.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventRepository {

    private final List<Event> events = new ArrayList<>();

    public void addRoomBookedEvent(RoomBookedEvent event) {
        events.add(event);
    }

    public void addRoomCreatedEvent(RoomCreatedEvent event) {
        events.add(event);
    }

    public void addCustomerCreatedEvent(CustomerCreatedEvent event) {
        events.add(event);
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    public void addQueryModelsDeletedEvent(QueryModelsDeletedEvent event) {
        events.add(event);
    }

    public void addBookingCanceledEvent(BookingCanceledEvent event) {
        events.add(event);
    }
}


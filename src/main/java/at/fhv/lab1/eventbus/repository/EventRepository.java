package at.fhv.lab1.eventbus.repository;

import at.fhv.lab1.eventbus.events.Event;
import at.fhv.lab1.eventbus.events.RoomAddedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventRepository {

    private final List<Event> events = new ArrayList<>();

    public void addRoomBookedEvent(RoomBookedEvent event) {
        events.add(event);
    }

    public void addRoomAddedEvent(RoomAddedEvent event) {
        events.add(event);
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    public void clearEvents() {
        events.clear();
        System.out.println("All events cleared from repository.");
    }
}


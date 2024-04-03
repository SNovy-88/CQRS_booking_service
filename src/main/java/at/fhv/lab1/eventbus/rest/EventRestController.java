package at.fhv.lab1.eventbus.rest;

import at.fhv.lab1.eventbus.EventPublisher;
import at.fhv.lab1.eventbus.events.Event;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.repository.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventRestController {

    private final EventRepository repository;

    private final EventPublisher publisher;

    public EventRestController(EventRepository repository, EventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @GetMapping("/event")
    public List<Event> getAllEvents() {
        return repository.getAllEvents();
    }

    @DeleteMapping("/event")
    public void deleteAllEvents() {
        repository.clearEvents();
    }

    @PostMapping(value = "/roombookedevent", consumes = "application/json")
    public boolean addRoomBookedEvent(@RequestBody RoomBookedEvent event) {
        repository.addRoomBookedEvent(event);
        System.out.println("Event received: " + event);
        return true;
    }
}

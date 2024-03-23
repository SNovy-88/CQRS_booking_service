package at.fhv.lab1.eventbus.rest;

import at.fhv.lab1.eventbus.events.Event;
import at.fhv.lab1.eventbus.repository.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventRestController {

    private final EventRepository repository;

    public EventRestController(EventRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/event", consumes = "application/json")
    public boolean addEvent(@RequestBody Event event) {
        // TODO: process event in repository
        repository.addEvent(event);
        System.out.println("Event received: " + event);
        return true;
    }

    @GetMapping("/event")
    public List<Event> getAllEvents() {
        return repository.getAllEvents();
    }

    @DeleteMapping("/event")
    public void deleteAllEvents() {
        repository.clearEvents();
    }
}

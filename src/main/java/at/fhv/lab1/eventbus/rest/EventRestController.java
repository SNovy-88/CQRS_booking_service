package at.fhv.lab1.eventbus.rest;

import at.fhv.lab1.eventbus.events.Event;
import at.fhv.lab1.eventbus.events.RoomAddedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.repository.EventRepository;
import at.fhv.lab1.queryclient.service.EventSubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventRestController {

    private final EventRepository repository;
    private final EventSubscriberService eventSubscriberService;


    @Autowired
    public EventRestController(EventRepository repository, EventSubscriberService eventSubscriberService) {
        this.repository = repository;
        this.eventSubscriberService = eventSubscriberService;
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

    @PostMapping(value = "/roomaddedevent", consumes = "application/json")
    public Boolean addNewRoomAddedEvent(@RequestBody RoomAddedEvent event) {
        repository.addRoomAddedEvent(event);
        eventSubscriberService.handleRoomAddedEvent(event);
        return true;
    }
}

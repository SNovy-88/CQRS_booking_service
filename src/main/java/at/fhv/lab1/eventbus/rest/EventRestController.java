package at.fhv.lab1.eventbus.rest;

import at.fhv.lab1.eventbus.EventPublisher;
import at.fhv.lab1.eventbus.events.*;
import at.fhv.lab1.eventbus.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventRestController {

    private final EventRepository repository;
    private final EventPublisher eventPublisher;


    @Autowired
    public EventRestController(EventRepository repository, EventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/event")
    public List<Event> getAllEvents() {
        return repository.getAllEvents();
    }

    @PostMapping(value = "/roombookedevent", consumes = "application/json")
    public boolean handleRoomBookedEvent(@RequestBody RoomBookedEvent event) {
        repository.addRoomBookedEvent(event);
        eventPublisher.publishEvent(event);
        return true;
    }

    @PostMapping(value = "/bookingcanceledevent", consumes = "application/json")
    public Boolean handleBookingCanceledEvent(@RequestBody BookingCanceledEvent event) {
        repository.addBookingCanceledEvent(event);
        eventPublisher.publishEvent(event);
        return true;
    }

    @PostMapping(value = "/roomcreatedevent", consumes = "application/json")
    public Boolean handleRoomCreatedEvent(@RequestBody RoomCreatedEvent event) {
        repository.addRoomCreatedEvent(event);
        eventPublisher.publishEvent(event);
        return true;
    }

    @PostMapping(value = "/customercreatedevent", consumes = "application/json")
    public Boolean handleCustomerCreatedEvent(@RequestBody CustomerCreatedEvent event) {
        repository.addCustomerCreatedEvent(event);
        eventPublisher.publishEvent(event);
        return true;
    }

    @PostMapping(value = "/querymodelsdeletedevent", consumes = "application/json")
    public Boolean handleQueryModelsDeletedEvent(@RequestBody QueryModelsDeletedEvent event) {
        repository.addQueryModelsDeletedEvent(event);
        eventPublisher.publishEvent(event);
        return true;
    }
}

package at.fhv.lab1;

import at.fhv.lab1.eventbus.events.RoomAddedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.queryclient.service.EventSubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Configuration
@ComponentScan("at.fhv.lab1.queryclient")
public class QueryClientApplication {

    private final EventSubscriberService eventSubscriberService;

    @Autowired
    public QueryClientApplication(EventSubscriberService eventSubscriberService) {
        this.eventSubscriberService = eventSubscriberService;
    }

    public static void main(String[] args) {
        SpringApplication.run(QueryClientApplication.class, args);
    }

    @EventListener
    public void handleRoomBookedEvent(RoomBookedEvent event) {
        eventSubscriberService.handleRoomBookedEvent(event);
    }

    @EventListener
    public void handleRoomAddedEvent(RoomAddedEvent event) {
        eventSubscriberService.handleRoomAddedEvent(event);
    }
}

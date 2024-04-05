package at.fhv.lab1.eventbus;

import at.fhv.lab1.eventbus.events.Event;
import at.fhv.lab1.eventbus.events.RoomAddedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class EventPublisher {

    private final WebClient localApiClient = WebClient.create("http://localhost:8080");


    public EventPublisher() {
    }

    public Boolean publishEvent(Event event) {
        return localApiClient
                .post()
                .uri("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event), Event.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public Boolean publishEvent(RoomBookedEvent event) {
        return localApiClient
                .post()
                .uri("/roombookedevent")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event), Event.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public Boolean publishEvent(RoomAddedEvent event) {
        return localApiClient
                .post()
                .uri("http://localhost:8081/roomaddedevent")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event), RoomAddedEvent.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}

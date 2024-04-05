package at.fhv.lab1.queryclient.service;

import at.fhv.lab1.eventbus.events.RoomAddedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventSubscriberService {

    private final RoomQueryService roomQueryService;

    @Autowired
    public EventSubscriberService(RoomQueryService roomQueryService) {
        this.roomQueryService = roomQueryService;
    }

    public void handleRoomBookedEvent(RoomBookedEvent event) {
        // Hier können Sie eine Projection erstellen und in Ihrer Read-Seite speichern
        // Beispiel:
        roomQueryService.updateFreeRoomsFromEvent(event);
    }

    public void handleRoomAddedEvent(RoomAddedEvent event) {
        roomQueryService.addRoomToRepository(event.getRoom());
    }
}

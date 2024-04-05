package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Room;
import at.fhv.lab1.eventbus.EventPublisher;
import at.fhv.lab1.eventbus.events.RoomAddedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomRepository {

    private final List<Room> rooms = new ArrayList<>();
    private final EventPublisher eventPublisher;

    @Autowired
    public RoomRepository(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public Boolean addRoom(Room room) {
        rooms.add(room);
        return eventPublisher.publishEvent(new RoomAddedEvent(room));
    }

}

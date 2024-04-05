package at.fhv.lab1.eventbus.events;

import at.fhv.lab1.commandclient.model.Room;

public class RoomAddedEvent extends Event {

    private final Room room;

    public RoomAddedEvent(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "RoomAddedEvent{" +
                "room=" + room +
                '}';
    }
}

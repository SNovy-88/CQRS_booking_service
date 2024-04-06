package at.fhv.lab1.eventbus.events;

import at.fhv.lab1.commandclient.model.Room;

public class RoomCreatedEvent extends Event {
    private final Room room;

    public RoomCreatedEvent(Room room) {
        super("New room with room number " + room.getId() + " created.");
        this.room = room;
    }

    public RoomCreatedEvent() {
        this.room = new Room();
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

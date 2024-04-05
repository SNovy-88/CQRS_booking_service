package at.fhv.lab1.eventbus.events;

import at.fhv.lab1.commandclient.model.Room;

public class RoomAddedEvent extends Event {
    private final Room room;

    public RoomAddedEvent(Room room) {
        super("New room with room number " + room.getRoomNumber() + " added.");
        this.room = room;
    }

    public RoomAddedEvent() {
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

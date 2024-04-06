package at.fhv.lab1.commandclient.model;

public class Room {
    boolean oceanView;
    private int roomNumber;
    private int capacity;

    public Room() {
    }

    public Room(int roomNumber, int capacity, boolean oceanView) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.oceanView = oceanView;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isOceanView() {
        return oceanView;
    }

    public void setOceanView(boolean oceanView) {
        this.oceanView = oceanView;
    }
}

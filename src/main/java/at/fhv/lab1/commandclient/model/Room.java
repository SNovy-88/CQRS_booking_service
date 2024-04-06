package at.fhv.lab1.commandclient.model;

public class Room {
    boolean oceanView;
    private int id;
    private int capacity;

    public Room() {
    }

    public Room(int id, int capacity, boolean oceanView) {
        this.id = id;
        this.capacity = capacity;
        this.oceanView = oceanView;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

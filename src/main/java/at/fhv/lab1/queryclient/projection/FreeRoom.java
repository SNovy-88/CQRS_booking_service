package at.fhv.lab1.queryclient.projection;

import java.time.LocalDate;

public class FreeRoom {
    private long roomNumber;
    private LocalDate fromDate;
    private LocalDate toDate;
    int capacity;

    public FreeRoom(long roomNumber, LocalDate fromDate, LocalDate toDate, int capacity) {
        this.roomNumber = roomNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.capacity = capacity;

    }

    public long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "FreeRoomDTO{" +
                "roomNumber=" + roomNumber +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}

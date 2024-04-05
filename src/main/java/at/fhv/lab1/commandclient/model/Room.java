package at.fhv.lab1.commandclient.model;

import java.time.LocalDate;
import java.util.List;

public class Room {
    boolean oceanView;
    private int roomNumber;
    private int capacity;
    private List<Booking> bookings;
    public Room() {
    }

    public Room(int roomNumber, int capacity, boolean oceanView) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.oceanView = oceanView;
    }

    public boolean isFree(LocalDate checkInDate, LocalDate checkOutDate){
        for (Booking booking : bookings) {
            if (booking.getCheckInDate().isBefore(checkOutDate) && booking.getCheckOutDate().isAfter(checkInDate)) {
                return false;
            }
        }
        return true;
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}

package at.fhv.lab1.queryclient.model;

import java.time.LocalDate;

public class BookingProjection {
    private long bookingID;
    private long customerID;
    private long roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private long nextBookingProjection = 0;

    public BookingProjection() {
    }

    public BookingProjection(long customerID, long roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingID = nextBookingProjection++;
        this.customerID = customerID;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public long getBookingID() {
        return bookingID;
    }

    public void setBookingID(long bookingID) {
        this.bookingID = bookingID;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public long getNextBookingProjection() {
        return nextBookingProjection;
    }

    public void setNextBookingProjection(long nextBookingProjection) {
        this.nextBookingProjection = nextBookingProjection;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}

package at.fhv.lab1.queryclient.projection;

import java.time.LocalDate;

public class BookingProjection {
    private long bookingID;
    private long customerID;
    private long roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public BookingProjection() {
    }

    public BookingProjection(long bookingID, long customerID, long roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingID = bookingID;
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

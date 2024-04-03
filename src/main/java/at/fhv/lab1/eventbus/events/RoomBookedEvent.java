/*
 * Copyright (c) 2024 Sarah N
 *
 * Project Name:         lab1template
 * Description:
 *
 * Date of Creation/
 * Last Update:          03/04/2024
 */

package at.fhv.lab1.eventbus.events;

import at.fhv.lab1.commandclient.model.Booking;
import at.fhv.lab1.commandclient.model.Customer;

import java.time.LocalDate;

public class RoomBookedEvent extends Event{
    private long bookingID;
    private Customer customer;
    private long roomID;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public RoomBookedEvent() {
    }

    public RoomBookedEvent(Booking booking){
        this.bookingID = booking.getBookingID();
        this.customer = booking.getCustomer();
        this.roomID = booking.getRoom().getRoomNumber();
        this.checkInDate = booking.getCheckInDate();
        this.checkOutDate = booking.getCheckOutDate();
    }

    public RoomBookedEvent(long bookingID, Customer customer, long roomID, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingID = bookingID;
        this.customer = customer;
        this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public long getBookingID() {
        return bookingID;
    }

    public void setBookingID(long bookingID) {
        this.bookingID = bookingID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
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

    @Override
    public String toString() {
        return "RoomBookedEvent{" +
                "bookingID=" + bookingID +
                ", customer=" + customer +
                ", roomID=" + roomID +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}

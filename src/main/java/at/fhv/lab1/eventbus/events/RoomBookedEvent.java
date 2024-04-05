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
import at.fhv.lab1.commandclient.model.Room;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

public class RoomBookedEvent extends Event{
    private long bookingID;
    private Customer customer;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public RoomBookedEvent() {
    }

    public RoomBookedEvent(Booking booking){
        this.bookingID = booking.getBookingID();
        this.customer = booking.getCustomer();
        this.room = booking.getRoom();
        this.checkInDate = booking.getCheckInDate();
        this.checkOutDate = booking.getCheckOutDate();
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

    public Room getRoom() {
        return room;
    }

    public void setRoomID(Room room) {
        this.room = room;
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
                ", room=" + room.getRoomNumber() +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}

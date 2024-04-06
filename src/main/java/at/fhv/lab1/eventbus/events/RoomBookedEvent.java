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

public class RoomBookedEvent extends Event {
    private Booking booking;

    public RoomBookedEvent() {
    }

    public RoomBookedEvent(Booking booking) {
        super("New booking event for room: " + booking.getRoom().getRoomNumber() + " by customer: " + booking.getCustomer().getName());
        this.booking = booking;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}

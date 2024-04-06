package at.fhv.lab1.eventbus.events;

import at.fhv.lab1.commandclient.model.Booking;

public class BookingCreatedEvent extends Event {
    private Booking booking;

    public BookingCreatedEvent() {
    }

    public BookingCreatedEvent(Booking booking) {
        super("Booking with ID " + booking.getBookingID() + " was created.");
        this.booking = booking;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}

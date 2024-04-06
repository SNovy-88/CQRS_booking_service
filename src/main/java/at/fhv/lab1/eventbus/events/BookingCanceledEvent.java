package at.fhv.lab1.eventbus.events;

import at.fhv.lab1.commandclient.model.Booking;

public class BookingCanceledEvent extends Event {
    private Booking booking;

    public BookingCanceledEvent() {
    }

    public BookingCanceledEvent(Booking booking) {
        super("Booking with ID " + booking.getBookingID() + " was canceled.");
        this.booking = booking;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}

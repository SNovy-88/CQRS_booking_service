package at.fhv.lab1.commandclient.service;

import at.fhv.lab1.commandclient.model.BookingRequest;

public interface IBookingService {
    boolean bookRoom(BookingRequest bookingRequest);
}

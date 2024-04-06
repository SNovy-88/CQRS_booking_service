package at.fhv.lab1.commandclient.service;

import at.fhv.lab1.commandclient.command.BookRoomCommand;
import at.fhv.lab1.commandclient.command.CancelBookingCommand;

public interface IBookingService {
    Boolean bookRoom(BookRoomCommand bookRoomCommand);

    boolean cancelBooking(CancelBookingCommand cancelBookingCommand);
}

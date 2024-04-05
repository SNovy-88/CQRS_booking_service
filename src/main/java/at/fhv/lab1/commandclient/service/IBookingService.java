package at.fhv.lab1.commandclient.service;

import at.fhv.lab1.commandclient.command.BookRoomCommand;

public interface IBookingService {
    boolean bookRoom(BookRoomCommand bookRoomCommand);
}

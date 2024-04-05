package at.fhv.lab1.commandclient.rest;

import at.fhv.lab1.commandclient.command.BookRoomCommand;
import at.fhv.lab1.commandclient.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookingRestController {
    private final BookingService bookingService;

    @Autowired
    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public String bookRoom(@RequestBody BookRoomCommand bookRoomCommand) {
        if (bookingService.bookRoom(bookRoomCommand)) {
            return "Room booked successfully!";
        } else {
            return "Invalid booking request.";
        }
    }
}

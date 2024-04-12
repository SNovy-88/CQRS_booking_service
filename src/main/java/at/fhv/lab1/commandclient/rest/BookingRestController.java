package at.fhv.lab1.commandclient.rest;

import at.fhv.lab1.commandclient.command.BookRoomCommand;
import at.fhv.lab1.commandclient.command.CancelBookingCommand;
import at.fhv.lab1.commandclient.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingRestController {
    private final BookingService bookingService;

    @Autowired
    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @CrossOrigin(origins = "http://localhost:8082")
    @PostMapping("/book")
    public ResponseEntity<String> bookRoom(@RequestBody BookRoomCommand bookRoomCommand) {
        if (bookingService.bookRoom(bookRoomCommand)) {
            return ResponseEntity.ok("Room booked successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid booking request.");
        }
    }

    @CrossOrigin(origins = "http://localhost:8082")
    @PostMapping("/cancel")
    public String cancelBooking(@RequestBody CancelBookingCommand cancelBookingCommand) {
        if (bookingService.cancelBooking(cancelBookingCommand)) {
            return "Booking canceled successfully!";
        } else {
            return "Invalid cancel request.";
        }
    }
}

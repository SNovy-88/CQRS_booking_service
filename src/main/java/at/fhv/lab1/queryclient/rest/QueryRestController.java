package at.fhv.lab1.queryclient.rest;

import at.fhv.lab1.eventbus.events.BookingCanceledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.events.RoomCreatedEvent;
import at.fhv.lab1.queryclient.model.BookingProjection;
import at.fhv.lab1.queryclient.model.CustomerProjection;
import at.fhv.lab1.queryclient.model.FreeRoom;
import at.fhv.lab1.queryclient.query.GetBookingsQuery;
import at.fhv.lab1.queryclient.query.GetCustomersQuery;
import at.fhv.lab1.queryclient.query.GetFreeRoomsQuery;
import at.fhv.lab1.queryclient.service.BookingQueryService;
import at.fhv.lab1.queryclient.service.CustomerQueryService;
import at.fhv.lab1.queryclient.service.RoomQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QueryRestController {
    private final RoomQueryService roomQueryService;
    private final CustomerQueryService customerQueryService;
    private final BookingQueryService bookingQueryService;

    @Autowired
    public QueryRestController(RoomQueryService roomQueryService, CustomerQueryService customerQueryService, BookingQueryService bookingQueryService) {
        this.roomQueryService = roomQueryService;
        this.customerQueryService = customerQueryService;
        this.bookingQueryService = bookingQueryService;
    }

    @PostMapping(value = "/roomCreatedEvent", consumes = "application/json")
    public Boolean handleRoomCreatedEvent(@RequestBody RoomCreatedEvent event) {
        return roomQueryService.addRoomToRepository(event);
    }

    @PostMapping(value = "/customerCreatedEvent", consumes = "application/json")
    public Boolean handleCustomerCreatedEvent(@RequestBody CustomerCreatedEvent event) {
        return customerQueryService.addCustomerToRepository(event);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(value = "/queryModelsDeletedEvent", consumes = "application/json")
    public boolean handleQueryModelsDeletedEvent() {
       boolean success = roomQueryService.deleteQueryModels();
       boolean success2 = customerQueryService.deleteQueryModels();
       boolean success3 = bookingQueryService.deleteQueryModels();

        return success && success2 && success3;
    }

    @PostMapping(value = "/roomBookedEvent", consumes = "application/json")
    public Boolean handleRoomBookedEvent(@RequestBody RoomBookedEvent event) {
        Boolean success = false;
        success = bookingQueryService.addBookingToRepository(event);
        success = roomQueryService.bookRoom(event);

        return success;
    }

    @PostMapping(value = "bookingCanceledEvent", consumes = "application/json")
    public Boolean handleBookingCanceledEvent(@RequestBody BookingCanceledEvent event) {
        Boolean success = false;
        success = bookingQueryService.cancelBooking(event);
        success = roomQueryService.cancelBooking(event);

        return success;
    }

     @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(value = "/freeRooms", consumes = "application/json")
    public List<FreeRoom> getFreeRooms(@RequestBody GetFreeRoomsQuery query) {
        List<FreeRoom> freeRooms = roomQueryService.getFreeRooms(query.getCheckInDate(), query.getCheckOutDate(), query.getCapacity());

        return freeRooms;
    }

    @GetMapping(value = "/freeRooms")
    public List<FreeRoom> getFreeRooms() {
        List<FreeRoom> freeRooms = roomQueryService.getAllFreeRooms();

        return freeRooms;
    }

    @PostMapping(value = "/bookings")
    public List<BookingProjection> getBookings(@RequestBody GetBookingsQuery query) {
        List<BookingProjection> bookings = bookingQueryService.getBookingsByDate(query.getFromDate(), query.getToDate());

        return bookings;
    }

    @GetMapping(value = "/customers", consumes = "application/json")
    public List<CustomerProjection> getBookings(@RequestBody GetCustomersQuery query) {
        List<CustomerProjection> customers = new ArrayList<>();
        if (query.getName() != null && !query.getName().isEmpty()) {
            customers = customerQueryService.getCustomersByName(query.getName());
        } else {
            customers = customerQueryService.getAllCustomers();
        }

        return customers;
    }
}

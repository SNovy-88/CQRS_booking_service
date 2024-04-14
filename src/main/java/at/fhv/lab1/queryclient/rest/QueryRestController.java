package at.fhv.lab1.queryclient.rest;

import at.fhv.lab1.eventbus.events.BookingCanceledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.events.RoomCreatedEvent;
import at.fhv.lab1.queryclient.projection.BookingProjection;
import at.fhv.lab1.queryclient.projection.CustomerProjection;
import at.fhv.lab1.queryclient.projection.FreeRoom;
import at.fhv.lab1.queryclient.query.GetBookingsQuery;
import at.fhv.lab1.queryclient.query.GetCustomerByNameQuery;
import at.fhv.lab1.queryclient.query.GetFreeRoomsQuery;
import at.fhv.lab1.queryclient.service.BookingQueryService;
import at.fhv.lab1.queryclient.service.CustomerQueryService;
import at.fhv.lab1.queryclient.service.RoomQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "/queryModelsDeletedEvent", consumes = "application/json")
    public boolean handleQueryModelsDeletedEvent() {
        boolean success = roomQueryService.deleteQueryModels();
        boolean success2 = customerQueryService.deleteQueryModels();
        boolean success3 = bookingQueryService.deleteQueryModels();

        return success && success2 && success3;
    }

    @PostMapping(value = "/roomBookedEvent", consumes = "application/json")
    public Boolean handleRoomBookedEvent(@RequestBody RoomBookedEvent event) {
        Boolean success1;
        success1 = bookingQueryService.addBookingToRepository(event);
        Boolean success2 = roomQueryService.bookRoom(event);

        return success1 && success2;
    }

    @PostMapping(value = "bookingCanceledEvent", consumes = "application/json")
    public Boolean handleBookingCanceledEvent(@RequestBody BookingCanceledEvent event) {
        Boolean success;
        success = bookingQueryService.cancelBooking(event);
        success = roomQueryService.cancelBooking(event);

        return success;
    }

    @PostMapping(value = "/freeRooms", consumes = "application/json")
    public List<FreeRoom> getFreeRooms(@RequestBody GetFreeRoomsQuery query) {
        List<FreeRoom> freeRooms;
        if (query.getCheckInDate() == null || query.getCheckOutDate() == null || query.getCapacity() == 0) {
            freeRooms = roomQueryService.getAllFreeRooms();
        } else {
            freeRooms = roomQueryService.getFreeRooms(query.getCheckInDate(), query.getCheckOutDate(), query.getCapacity());
        }
        return freeRooms;
    }

    @PostMapping(value = "/bookingsByDate", consumes = "application/json")
    public List<BookingProjection> getBookings(@RequestBody GetBookingsQuery query) {
        List<BookingProjection> bookings = bookingQueryService.getBookingsByDate(query.getFromDate(), query.getToDate());

        return bookings;
    }

    @GetMapping(value = "/bookings")
    public List<BookingProjection> getAllBookings() {
        List<BookingProjection> bookings = bookingQueryService.getAllBookings();

        return bookings;
    }

    @PostMapping(value = "/customerByName", consumes = "application/json")
    public List<CustomerProjection> getCustomersByName(@RequestBody GetCustomerByNameQuery query) {
        List<CustomerProjection> customers;
        if (query.getName() != null && !query.getName().isEmpty()) {
            customers = customerQueryService.getCustomersByName(query.getName());
        } else {
            customers = customerQueryService.getAllCustomers();
        }

        return customers;
    }

    @GetMapping(value = "/customers")
    public List<CustomerProjection> getCustomers() {
        List<CustomerProjection> allCustomers = customerQueryService.getAllCustomers();
        return allCustomers;
    }

}

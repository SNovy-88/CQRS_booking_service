/*
 * Copyright (c) 2024 Sarah N
 *
 * Project Name:         lab1template
 * Description:
 *
 * Date of Creation/
 * Last Update:          03/04/2024
 */

package at.fhv.lab1.queryclient.rest;

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
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> 5ea20cbf30939c200f4a97fce85da3e8ae9ba113

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/query")
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
    public Boolean handleQueryModelsDeletedEvent() {
        Boolean success = false;

        success = roomQueryService.deleteQueryModels();
        success = customerQueryService.deleteQueryModels();
        success = bookingQueryService.deleteQueryModels();

        return success;
    }

    @PostMapping(value = "/roomBookedEvent", consumes = "application/json")
    public Boolean handleRoomBookedEvent(@RequestBody RoomBookedEvent event) {
        return roomQueryService.bookRoom(event);
    }

    @PostMapping(value = "/freeRooms", consumes = "application/json")
    public List<FreeRoom> getFreeRooms(@RequestBody GetFreeRoomsQuery query) {
        List<FreeRoom> freeRooms = roomQueryService.getFreeRooms(query.getCheckInDate(), query.getCheckOutDate(), query.getCapacity());

        return freeRooms;
    }

    @GetMapping(value = "/bookings", consumes = "application/json")
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

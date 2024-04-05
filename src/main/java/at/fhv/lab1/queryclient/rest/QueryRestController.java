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

import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.queryclient.model.FreeRoom;
import at.fhv.lab1.queryclient.query.GetFreeRoomsQuery;
import at.fhv.lab1.queryclient.repository.RoomReadRepository;
import at.fhv.lab1.queryclient.service.RoomQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueryRestController {
    private final RoomReadRepository roomRepository;

    public QueryRestController(RoomReadRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @PostMapping(value = "/event", consumes = "application/json")
    public boolean addEvent(@RequestBody RoomBookedEvent event) {
        // TODO: process event through projection
        System.out.println("Event received: " + event);
        return true;
    }

    @GetMapping(value = "/freeRooms", consumes = "application/json")
    public List<FreeRoom> getFreeRooms(@RequestBody GetFreeRoomsQuery query) {
        RoomQueryService roomQueryService = new RoomQueryService(roomRepository);
        List<FreeRoom> freeRooms = roomQueryService.getFreeRooms(query.getCheckInDate(), query.getCheckOutDate(), query.getCapacity());

        return freeRooms;
    }
}

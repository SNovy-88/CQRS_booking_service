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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryRestController {


    public QueryRestController() {

    }

    @PostMapping(value = "/event", consumes = "application/json")
    public boolean addEvent(@RequestBody RoomBookedEvent event) {
        // TODO: process event through projection
        System.out.println("Event received: " + event);
        return true;
    }
}

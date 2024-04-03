/*
 * Copyright (c) 2024 Sarah N
 *
 * Project Name:         lab1template
 * Description:
 *
 * Date of Creation/
 * Last Update:          03/04/2024
 */

package at.fhv.lab1.eventbus.events;

import at.fhv.lab1.commandclient.model.Room;

public class RoomCreatedEvent extends Event{

    private Room room;

    public RoomCreatedEvent() {
    }

    public RoomCreatedEvent(Room room) {
        this.room = room;
    }




}

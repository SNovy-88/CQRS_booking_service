/*
 * Copyright (c) 2024 Sarah N
 *
 * Project Name:         lab1template
 * Description:
 *
 * Date of Creation/
 * Last Update:          03/04/2024
 */

package at.fhv.lab1.queryclient.query;

import java.time.LocalDate;

public class GetFreeRoomsQuery {

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int capacity;

    public GetFreeRoomsQuery() {
    }

    public GetFreeRoomsQuery(LocalDate checkInDate, LocalDate checkOutDate, int capacity) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.capacity = capacity;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

/*
 * Copyright (c) 2024 Sarah N
 *
 * Project Name:         lab1template
 * Description:
 *
 * Date of Creation/
 * Last Update:          03/04/2024
 */

package at.fhv.lab1.queryclient.repository;

import at.fhv.lab1.queryclient.model.BookingProjection;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class BookingReadRepository implements IBookingReadRepository {
    List<BookingProjection> bookingProjections;

    @Override
    public Boolean addBooking(BookingProjection bookingProjection) {
        bookingProjections.add(bookingProjection);

        return true;
    }

    @Override
    public Boolean deleteQueryModels() {
        if (bookingProjections != null && !bookingProjections.isEmpty()) {
            bookingProjections.clear();

            return true;
        } else {
            return false;
        }
    }

    public List<BookingProjection> getBookingsByDate(LocalDate fromDate, LocalDate toDate) {
        for (BookingProjection bookingProjection : bookingProjections) {
            if (bookingProjection.getCheckInDate().isAfter(fromDate) && bookingProjection.getCheckOutDate().isBefore(toDate)) {
                bookingProjections.add(bookingProjection);
            }
        }

        return bookingProjections;
    }
}

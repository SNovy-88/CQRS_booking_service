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
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingReadRepository implements IBookingReadRepository {
    List<BookingProjection> bookingProjections = new ArrayList<>();

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
        List<BookingProjection> bookingProjectionList = new ArrayList<>();
        for (BookingProjection bookingProjection : bookingProjections) {
            if ((bookingProjection.getCheckInDate().isAfter(fromDate) ||
                    bookingProjection.getCheckOutDate().isEqual(fromDate)) &&
                    (bookingProjection.getCheckOutDate().isBefore(toDate)) ||
                    bookingProjection.getCheckOutDate().isEqual(toDate)) {
                bookingProjectionList.add(bookingProjection);
            }
        }

        return bookingProjectionList;
    }

    @Override
    public Boolean cancelBooking(long bookingID) {
        for (BookingProjection bookingProjection : bookingProjections) {
            if (bookingProjection.getBookingID() == bookingID) {
                bookingProjections.remove(bookingProjection);

                return true;
            }
        }

        return false;
    }
}

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

import at.fhv.lab1.queryclient.projection.BookingProjection;
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
        System.out.println("Booking added to repository");
        return true;
    }

    @Override
    public List<BookingProjection> getAllBookings() {
        return bookingProjections;
    }

    @Override
    public boolean deleteQueryModels() {
        if (bookingProjections != null && !bookingProjections.isEmpty()) {
            bookingProjections.clear();

            return true;
        } else {
            return bookingProjections.isEmpty();
        }
    }

    //TODO what if no date is inputted?
    public List<BookingProjection> getBookingsByDate(LocalDate fromDate, LocalDate toDate) {
        List<BookingProjection> bookingProjectionList = new ArrayList<>();
        for (BookingProjection bookingProjection : bookingProjections) {
            if ((bookingProjection.getCheckInDate().isAfter(fromDate) ||
                    bookingProjection.getCheckInDate().isEqual(fromDate)) &&
                    (bookingProjection.getCheckOutDate().isBefore(toDate) ||
                    bookingProjection.getCheckOutDate().isEqual(toDate))) {
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

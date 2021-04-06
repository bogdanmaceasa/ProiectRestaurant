package com.siit.finalproject.booking.comparator;

import com.siit.finalproject.booking.model.Entities.BookingEntity;

import java.util.Comparator;

public class ComparatorForBooking implements Comparator<BookingEntity> {
    @Override
    public int compare(BookingEntity o1, BookingEntity o2) {
        return o1.getBookingDate().compareTo(o2.getBookingDate());
    }
}

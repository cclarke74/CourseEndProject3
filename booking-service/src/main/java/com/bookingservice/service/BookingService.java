package com.bookingservice.service;

import com.bookingservice.dto.BookingRequest;
import com.bookingservice.dto.BookingResponse;
import com.bookingservice.entity.BookingEntity;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest booking);

    BookingEntity getBookingById(Long id);


    List<BookingEntity> getBookingsForUser(String userName);
}

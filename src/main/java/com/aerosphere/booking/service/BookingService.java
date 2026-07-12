package com.aerosphere.booking.service;

import com.aerosphere.booking.dto.request.BookingRequest;
import com.aerosphere.booking.dto.response.BookingResponse;

import java.util.List;

/**
 * Purpose:
 * Defines business operations for booking management.
 *
 * Responsibilities:
 * - Create bookings.
 * - Retrieve bookings.
 * - Update bookings.
 * - Delete bookings.
 *
 * Module:
 * Booking
 */
public interface BookingService {

    BookingResponse createBooking(BookingRequest request);

    List<BookingResponse> getAllBookings();

    BookingResponse getBookingById(Long id);

    BookingResponse updateBooking(Long id, BookingRequest request);

    void deleteBooking(Long id);

}
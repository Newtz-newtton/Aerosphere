package com.aerosphere.booking.mapper;

import com.aerosphere.booking.dto.response.BookingResponse;
import com.aerosphere.booking.entity.Booking;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Converts Booking entities into BookingResponse DTOs.
 *
 * Responsibilities:
 * - Transform Booking entity to response DTO.
 * - Expose business-friendly information.
 * - Hide internal entity implementation.
 *
 * Module:
 * Booking
 */
@Component
public class BookingMapper {

    public BookingResponse toResponse(Booking booking) {

        return BookingResponse.builder()
                .id(booking.getId())
                .bookingReference(booking.getBookingReference())

                .userId(booking.getUser().getId())
                .customerName(
                        booking.getUser().getFirstName() + " "
                                + booking.getUser().getLastName()
                )

                .flightId(booking.getFlight().getId())
                .flightNumber(booking.getFlight().getFlightNumber())

                .bookingStatus(booking.getBookingStatus())
                .totalFare(booking.getTotalFare())
                .bookingDate(booking.getBookingDate())
                .build();
    }

}
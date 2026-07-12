package com.aerosphere.booking.dto.response;

import com.aerosphere.booking.entity.BookingStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Purpose:
 * Represents booking information returned to the client.
 *
 * Responsibilities:
 * - Transfer booking details.
 * - Hide internal entity implementation.
 * - Present business-friendly booking information.
 *
 * Module:
 * Booking
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {

    private Long id;

    private String bookingReference;

    private Long userId;

    private String customerName;

    private Long flightId;

    private String flightNumber;

    private BookingStatus bookingStatus;

    private BigDecimal totalFare;

    private LocalDateTime bookingDate;

}
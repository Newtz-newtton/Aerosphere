package com.aerosphere.booking.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

/**
 * Purpose:
 * Represents the client request for creating or updating a booking.
 *
 * Responsibilities:
 * - Capture booking request data.
 * - Validate mandatory booking fields.
 * - Transfer booking data to the service layer.
 *
 * Module:
 * Booking
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {

    @NotNull(message = "User is required.")
    private Long userId;

    @NotNull(message = "Flight is required.")
    private Long flightId;

    @NotNull(message = "Total fare is required.")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Total fare must be greater than zero.")
    private BigDecimal totalFare;

}
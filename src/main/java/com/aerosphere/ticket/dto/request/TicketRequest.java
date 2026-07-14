package com.aerosphere.ticket.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Purpose:
 * Represents the request to generate an airline ticket.
 *
 * Responsibilities:
 * - Accept ticket generation request.
 * - Identify the booking for ticket issuance.
 *
 * Module:
 * Ticket
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketRequest {

    @NotNull(message = "Booking ID is required.")
    private Long bookingId;

}
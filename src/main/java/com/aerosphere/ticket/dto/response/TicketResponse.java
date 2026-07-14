package com.aerosphere.ticket.dto.response;

import com.aerosphere.ticket.entity.TicketStatus;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Purpose:
 * Represents ticket information returned to the client.
 *
 * Responsibilities:
 * - Transfer ticket details.
 * - Hide internal entity implementation.
 * - Present customer-friendly ticket information.
 *
 * Module:
 * Ticket
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {

    private Long id;

    private String ticketNumber;

    private Long bookingId;

    private String bookingReference;

    private String passengerName;

    private String flightNumber;

    private TicketStatus ticketStatus;

    private LocalDateTime issuedAt;

}
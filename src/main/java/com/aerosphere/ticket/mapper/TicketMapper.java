package com.aerosphere.ticket.mapper;

import com.aerosphere.ticket.dto.response.TicketResponse;
import com.aerosphere.ticket.entity.Ticket;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Converts Ticket entities into response DTOs.
 *
 * Responsibilities:
 * - Transform Ticket entities.
 * - Build customer-friendly response objects.
 * - Hide persistence implementation.
 *
 * Module:
 * Ticket
 */
@Component
public class TicketMapper {

    public TicketResponse toResponse(Ticket ticket) {

        if (ticket == null) {
            return null;
        }

        return TicketResponse.builder()
                .id(ticket.getId())
                .ticketNumber(ticket.getTicketNumber())
                .bookingId(ticket.getBooking().getId())
                .bookingReference(
                        ticket.getBooking().getBookingReference())
                .passengerName(ticket.getPassengerName())
                .flightNumber(ticket.getFlightNumber())
                .ticketStatus(ticket.getTicketStatus())
                .issuedAt(ticket.getIssuedAt())
                .build();
    }

}
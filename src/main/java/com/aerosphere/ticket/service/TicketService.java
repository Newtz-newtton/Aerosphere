package com.aerosphere.ticket.service;

import com.aerosphere.ticket.dto.request.TicketRequest;
import com.aerosphere.ticket.dto.response.TicketResponse;

import java.util.List;

/**
 * Purpose:
 * Defines business operations for ticket management.
 *
 * Responsibilities:
 * - Create tickets.
 * - Retrieve tickets.
 * - Update tickets.
 * - Delete tickets.
 *
 * Module:
 * Ticket
 */
public interface TicketService {

    TicketResponse createTicket(TicketRequest request);

    List<TicketResponse> getAllTickets();

    TicketResponse getTicketById(Long id);

    TicketResponse updateTicket(Long id,
                                TicketRequest request);

    void deleteTicket(Long id);

}
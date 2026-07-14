package com.aerosphere.ticket.controller;

import com.aerosphere.common.dto.ApiResponse;
import com.aerosphere.ticket.dto.request.TicketRequest;
import com.aerosphere.ticket.dto.response.TicketResponse;
import com.aerosphere.ticket.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Purpose:
 * Exposes REST APIs for ticket management.
 *
 * Responsibilities:
 * - Handle ticket CRUD requests.
 * - Delegate business operations to the service layer.
 * - Return standardized API responses.
 *
 * Module:
 * Ticket
 */
@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TicketResponse> createTicket(
            @Valid @RequestBody TicketRequest request) {

        TicketResponse response =
                ticketService.createTicket(request);

        return ApiResponse.<TicketResponse>builder()
                .success(true)
                .message("Ticket generated successfully.")
                .data(response)
                .build();
    }

    @GetMapping
    public ApiResponse<List<TicketResponse>> getAllTickets() {

        List<TicketResponse> response =
                ticketService.getAllTickets();

        return ApiResponse.<List<TicketResponse>>builder()
                .success(true)
                .message("Tickets retrieved successfully.")
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<TicketResponse> getTicketById(
            @PathVariable Long id) {

        TicketResponse response =
                ticketService.getTicketById(id);

        return ApiResponse.<TicketResponse>builder()
                .success(true)
                .message("Ticket retrieved successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    @PutMapping("/{id}")
    public ApiResponse<TicketResponse> updateTicket(
            @PathVariable Long id,
            @Valid @RequestBody TicketRequest request) {

        TicketResponse response =
                ticketService.updateTicket(id, request);

        return ApiResponse.<TicketResponse>builder()
                .success(true)
                .message("Ticket updated successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTicket(
            @PathVariable Long id) {

        ticketService.deleteTicket(id);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Ticket deleted successfully.")
                .build();
    }
}
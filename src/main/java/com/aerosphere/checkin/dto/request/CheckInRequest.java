package com.aerosphere.checkin.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Purpose:
 * Represents a client request to perform passenger check-in.
 *
 * Responsibilities:
 * - Accept ticket identifier.
 *
 * Module:
 * Check-in
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInRequest {

    @NotNull(message = "Ticket ID is required.")
    private Long ticketId;

}
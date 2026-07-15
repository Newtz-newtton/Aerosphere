package com.aerosphere.checkin.dto.response;

import com.aerosphere.checkin.entity.CheckInStatus;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Purpose:
 * Represents a check-in response returned to clients.
 *
 * Responsibilities:
 * - Return complete check-in information.
 * - Hide persistence layer details.
 *
 * Module:
 * Check-in
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInResponse {

    private Long id;

    private Long ticketId;

    private String ticketNumber;

    private String passengerName;

    private String flightNumber;

    private CheckInStatus checkInStatus;

    private LocalDateTime checkedInAt;

}
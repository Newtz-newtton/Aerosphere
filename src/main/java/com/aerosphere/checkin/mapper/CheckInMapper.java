package com.aerosphere.checkin.mapper;

import com.aerosphere.checkin.dto.response.CheckInResponse;
import com.aerosphere.checkin.entity.CheckIn;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Maps CheckIn entities to response DTOs.
 *
 * Responsibilities:
 * - Convert CheckIn entities into API responses.
 * - Hide persistence layer implementation.
 *
 * Module:
 * Check-in
 */
@Component
public class CheckInMapper {

    public CheckInResponse toResponse(CheckIn checkIn) {

        return CheckInResponse.builder()
                .id(checkIn.getId())
                .ticketId(checkIn.getTicket().getId())
                .ticketNumber(checkIn.getTicket().getTicketNumber())
                .passengerName(checkIn.getPassengerName())
                .flightNumber(checkIn.getFlightNumber())
                .checkInStatus(checkIn.getCheckInStatus())
                .checkedInAt(checkIn.getCheckedInAt())
                .build();
    }

}
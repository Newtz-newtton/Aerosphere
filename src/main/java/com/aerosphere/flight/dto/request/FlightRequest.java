package com.aerosphere.flight.dto.request;

import com.aerosphere.flight.entity.FlightStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Purpose:
 * Represents the request payload for creating or updating a flight.
 *
 * Responsibilities:
 * - Receive flight information from the client.
 * - Validate flight input.
 *
 * Module:
 * Flight
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightRequest {

    @NotBlank(message = "Flight number is required.")
    private String flightNumber;

    @NotNull(message = "Airline is required.")
    private Long airlineId;

    @NotNull(message = "Aircraft is required.")
    private Long aircraftId;

    @NotNull(message = "Departure airport is required.")
    private Long departureAirportId;

    @NotNull(message = "Arrival airport is required.")
    private Long arrivalAirportId;

    @NotNull(message = "Departure time is required.")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is required.")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Flight status is required.")
    private FlightStatus status;

}
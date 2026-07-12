package com.aerosphere.flight.dto.response;

import com.aerosphere.flight.entity.FlightStatus;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Purpose:
 * Represents the response returned for Flight APIs.
 *
 * Responsibilities:
 * - Expose business-friendly flight information.
 * - Return descriptive information from related entities.
 * - Hide internal database relationships.
 *
 * Module:
 * Flight
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightResponse {

    private Long id;

    private String flightNumber;

    private String airlineCode;

    private String airlineName;

    private String aircraftRegistrationNumber;

    private String aircraftManufacturer;

    private String aircraftModel;

    private String departureAirportCode;

    private String departureAirportName;

    private String arrivalAirportCode;

    private String arrivalAirportName;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private FlightStatus status;

}
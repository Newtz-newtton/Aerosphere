package com.aerosphere.flight.mapper;

import com.aerosphere.flight.dto.response.FlightResponse;
import com.aerosphere.flight.entity.Flight;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Converts Flight entities into response DTOs.
 *
 * Responsibilities:
 * - Map Flight entity data.
 * - Flatten related entity information.
 * - Return business-friendly API responses.
 *
 * Module:
 * Flight
 */
@Component
public class FlightMapper {

    public FlightResponse toResponse(Flight flight) {

        return FlightResponse.builder()

                .id(flight.getId())

                .flightNumber(flight.getFlightNumber())

                .airlineCode(
                        flight.getAirline().getAirlineCode())

                .airlineName(
                        flight.getAirline().getAirlineName())

                .aircraftRegistrationNumber(
                        flight.getAircraft().getRegistrationNumber())

                .aircraftManufacturer(
                        flight.getAircraft().getManufacturer())

                .aircraftModel(
                        flight.getAircraft().getModel())

                .departureAirportCode(
                        flight.getDepartureAirport().getAirportCode())

                .departureAirportName(
                        flight.getDepartureAirport().getAirportName())

                .arrivalAirportCode(
                        flight.getArrivalAirport().getAirportCode())

                .arrivalAirportName(
                        flight.getArrivalAirport().getAirportName())

                .departureTime(
                        flight.getDepartureTime())

                .arrivalTime(
                        flight.getArrivalTime())

                .status(
                        flight.getStatus())

                .build();
    }

}
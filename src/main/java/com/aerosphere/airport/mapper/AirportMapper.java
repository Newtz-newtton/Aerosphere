package com.aerosphere.airport.mapper;

import com.aerosphere.airport.dto.request.AirportRequest;
import com.aerosphere.airport.dto.response.AirportResponse;
import com.aerosphere.airport.entity.Airport;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Converts Airport entities to DTOs and DTOs to entities.
 *
 * Responsibilities:
 * - Convert AirportRequest to Airport.
 * - Convert Airport to AirportResponse.
 * - Centralize object mapping logic.
 *
 * Module:
 * Airport
 */
@Component
public class AirportMapper {

    public Airport toEntity(AirportRequest request) {

        return Airport.builder()
                .airportCode(request.getAirportCode())
                .airportName(request.getAirportName())
                .city(request.getCity())
                .country(request.getCountry())
                .timezone(request.getTimezone())
                .status(request.getStatus())
                .build();
    }

    public AirportResponse toResponse(Airport airport) {

        return AirportResponse.builder()
                .id(airport.getId())
                .airportCode(airport.getAirportCode())
                .airportName(airport.getAirportName())
                .city(airport.getCity())
                .country(airport.getCountry())
                .timezone(airport.getTimezone())
                .status(airport.getStatus())
                .build();
    }

}
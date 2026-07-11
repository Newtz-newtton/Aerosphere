package com.aerosphere.airline.mapper;

import com.aerosphere.airline.dto.request.AirlineRequest;
import com.aerosphere.airline.dto.response.AirlineResponse;
import com.aerosphere.airline.entity.Airline;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Converts Airline entities to DTOs and DTOs to entities.
 *
 * Responsibilities:
 * - Convert AirlineRequest to Airline.
 * - Convert Airline to AirlineResponse.
 * - Centralize object mapping logic.
 *
 * Module:
 * Airline
 */
@Component
public class AirlineMapper {

    public Airline toEntity(AirlineRequest request) {

        return Airline.builder()
                .airlineCode(request.getAirlineCode())
                .airlineName(request.getAirlineName())
                .country(request.getCountry())
                .status(request.getStatus())
                .build();
    }

    public AirlineResponse toResponse(Airline airline) {

        return AirlineResponse.builder()
                .id(airline.getId())
                .airlineCode(airline.getAirlineCode())
                .airlineName(airline.getAirlineName())
                .country(airline.getCountry())
                .status(airline.getStatus())
                .build();
    }

}
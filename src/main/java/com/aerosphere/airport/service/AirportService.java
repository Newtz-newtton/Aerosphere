package com.aerosphere.airport.service;

import com.aerosphere.airport.dto.request.AirportRequest;
import com.aerosphere.airport.dto.response.AirportResponse;

import java.util.List;

/**
 * Purpose:
 * Defines the business operations for airport management.
 *
 * Responsibilities:
 * - Create airports.
 * - Retrieve airport information.
 * - Update airport details.
 * - Delete airports.
 *
 * Module:
 * Airport
 */
public interface AirportService {

    AirportResponse createAirport(AirportRequest request);

    List<AirportResponse> getAllAirports();

    AirportResponse getAirportById(Long id);

    AirportResponse updateAirport(
            Long id,
            AirportRequest request);

    void deleteAirport(Long id);

}
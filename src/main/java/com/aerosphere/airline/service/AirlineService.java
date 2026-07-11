package com.aerosphere.airline.service;

import com.aerosphere.airline.dto.request.AirlineRequest;
import com.aerosphere.airline.dto.response.AirlineResponse;

import java.util.List;

/**
 * Purpose:
 * Defines the business operations for airline management.
 *
 * Responsibilities:
 * - Create airlines.
 * - Retrieve airline information.
 * - Update airline details.
 * - Delete airlines.
 *
 * Module:
 * Airline
 */
public interface AirlineService {

    AirlineResponse createAirline(AirlineRequest request);

    List<AirlineResponse> getAllAirlines();

    AirlineResponse getAirlineById(Long id);

    AirlineResponse updateAirline(
            Long id,
            AirlineRequest request);

    void deleteAirline(Long id);

}
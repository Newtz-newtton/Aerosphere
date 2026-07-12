package com.aerosphere.flight.service;

import com.aerosphere.flight.dto.request.FlightRequest;
import com.aerosphere.flight.dto.response.FlightResponse;

import java.util.List;

/**
 * Purpose:
 * Defines business operations for Flight management.
 *
 * Responsibilities:
 * - Manage Flight CRUD operations.
 * - Coordinate business validations.
 *
 * Module:
 * Flight
 */
public interface FlightService {

    FlightResponse createFlight(FlightRequest request);

    List<FlightResponse> getAllFlights();

    FlightResponse getFlightById(Long id);

    FlightResponse updateFlight(Long id,
                                FlightRequest request);

    void deleteFlight(Long id);

}
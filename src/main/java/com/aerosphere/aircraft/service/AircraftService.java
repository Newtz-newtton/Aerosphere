package com.aerosphere.aircraft.service;

import com.aerosphere.aircraft.dto.request.AircraftRequest;
import com.aerosphere.aircraft.dto.response.AircraftResponse;

import java.util.List;

/**
 * Purpose:
 * Defines the business operations for aircraft management.
 *
 * Responsibilities:
 * - Create aircraft.
 * - Retrieve aircraft.
 * - Update aircraft.
 * - Delete aircraft.
 *
 * Module:
 * Aircraft
 */
public interface AircraftService {

    AircraftResponse createAircraft(AircraftRequest request);

    List<AircraftResponse> getAllAircraft();

    AircraftResponse getAircraftById(Long id);

    AircraftResponse updateAircraft(
            Long id,
            AircraftRequest request);

    void deleteAircraft(Long id);

}
package com.aerosphere.aircraft.mapper;

import com.aerosphere.aircraft.dto.request.AircraftRequest;
import com.aerosphere.aircraft.dto.response.AircraftResponse;
import com.aerosphere.aircraft.entity.Aircraft;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Converts Aircraft entities to DTOs and DTOs to entities.
 *
 * Responsibilities:
 * - Convert AircraftRequest to Aircraft.
 * - Convert Aircraft to AircraftResponse.
 * - Centralize object mapping logic.
 *
 * Module:
 * Aircraft
 */
@Component
public class AircraftMapper {

    public Aircraft toEntity(AircraftRequest request) {

        return Aircraft.builder()
                .registrationNumber(request.getRegistrationNumber())
                .manufacturer(request.getManufacturer())
                .model(request.getModel())
                .seatingCapacity(request.getSeatingCapacity())
                .status(request.getStatus())
                .build();
    }

    public AircraftResponse toResponse(Aircraft aircraft) {

        return AircraftResponse.builder()
                .id(aircraft.getId())
                .registrationNumber(aircraft.getRegistrationNumber())
                .manufacturer(aircraft.getManufacturer())
                .model(aircraft.getModel())
                .seatingCapacity(aircraft.getSeatingCapacity())
                .status(aircraft.getStatus())
                .build();
    }

}
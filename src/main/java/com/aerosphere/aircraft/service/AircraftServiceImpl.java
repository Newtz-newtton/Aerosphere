package com.aerosphere.aircraft.service;

import com.aerosphere.aircraft.dto.request.AircraftRequest;
import com.aerosphere.aircraft.dto.response.AircraftResponse;
import com.aerosphere.aircraft.entity.Aircraft;
import com.aerosphere.aircraft.mapper.AircraftMapper;
import com.aerosphere.aircraft.repository.AircraftRepository;
import com.aerosphere.exception.custom.BusinessException;
import com.aerosphere.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Purpose:
 * Implements the business operations for aircraft management.
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
@Service
@RequiredArgsConstructor
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final AircraftMapper aircraftMapper;

    @Override
    public AircraftResponse createAircraft(AircraftRequest request) {

        if (aircraftRepository.existsByRegistrationNumber(
                request.getRegistrationNumber())) {

            throw new BusinessException(
                    "Registration number already exists.");
        }

        Aircraft aircraft =
                aircraftMapper.toEntity(request);

        Aircraft savedAircraft =
                aircraftRepository.save(aircraft);

        return aircraftMapper.toResponse(savedAircraft);
    }

    @Override
    public List<AircraftResponse> getAllAircraft() {

        return aircraftRepository.findAll()
                .stream()
                .map(aircraftMapper::toResponse)
                .toList();
    }

    @Override
    public AircraftResponse getAircraftById(Long id) {

        Aircraft aircraft =
                aircraftRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Aircraft not found."));

        return aircraftMapper.toResponse(aircraft);
    }

    @Override
    public AircraftResponse updateAircraft(
            Long id,
            AircraftRequest request) {

        Aircraft aircraft =
                aircraftRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Aircraft not found."));

        if (!aircraft.getRegistrationNumber().equals(
                request.getRegistrationNumber())
                &&
                aircraftRepository.existsByRegistrationNumber(
                        request.getRegistrationNumber())) {

            throw new BusinessException(
                    "Registration number already exists.");
        }

        aircraft.setRegistrationNumber(
                request.getRegistrationNumber());

        aircraft.setManufacturer(
                request.getManufacturer());

        aircraft.setModel(
                request.getModel());

        aircraft.setSeatingCapacity(
                request.getSeatingCapacity());

        aircraft.setStatus(
                request.getStatus());

        Aircraft updatedAircraft =
                aircraftRepository.save(aircraft);

        return aircraftMapper.toResponse(updatedAircraft);
    }

    @Override
    public void deleteAircraft(Long id) {

        Aircraft aircraft =
                aircraftRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Aircraft not found."));

        aircraftRepository.delete(aircraft);
    }

}
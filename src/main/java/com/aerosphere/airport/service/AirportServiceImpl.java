package com.aerosphere.airport.service;

import com.aerosphere.airport.dto.request.AirportRequest;
import com.aerosphere.airport.dto.response.AirportResponse;
import com.aerosphere.airport.entity.Airport;
import com.aerosphere.airport.mapper.AirportMapper;
import com.aerosphere.airport.repository.AirportRepository;
import com.aerosphere.exception.custom.BusinessException;
import com.aerosphere.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Purpose:
 * Implements the business operations for airport management.
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
@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Override
    public AirportResponse createAirport(AirportRequest request) {

        if (airportRepository.existsByAirportCode(
                request.getAirportCode())) {

            throw new BusinessException(
                    "Airport code already exists.");
        }

        Airport airport =
                airportMapper.toEntity(request);

        Airport savedAirport =
                airportRepository.save(airport);

        return airportMapper.toResponse(savedAirport);
    }

    @Override
    public List<AirportResponse> getAllAirports() {

        return airportRepository.findAll()
                .stream()
                .map(airportMapper::toResponse)
                .toList();
    }

    @Override
    public AirportResponse getAirportById(Long id) {

        Airport airport =
                airportRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Airport not found."));

        return airportMapper.toResponse(airport);
    }

    @Override
    public AirportResponse updateAirport(
            Long id,
            AirportRequest request) {

        Airport airport =
                airportRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Airport not found."));

        if (!airport.getAirportCode().equals(
                request.getAirportCode())
                &&
                airportRepository.existsByAirportCode(
                        request.getAirportCode())) {

            throw new BusinessException(
                    "Airport code already exists.");
        }

        airport.setAirportCode(request.getAirportCode());
        airport.setAirportName(request.getAirportName());
        airport.setCity(request.getCity());
        airport.setCountry(request.getCountry());
        airport.setTimezone(request.getTimezone());
        airport.setStatus(request.getStatus());

        Airport updatedAirport =
                airportRepository.save(airport);

        return airportMapper.toResponse(updatedAirport);
    }

    @Override
    public void deleteAirport(Long id) {

        Airport airport =
                airportRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Airport not found."));

        airportRepository.delete(airport);
    }

}
package com.aerosphere.airline.service;

import com.aerosphere.airline.dto.request.AirlineRequest;
import com.aerosphere.airline.dto.response.AirlineResponse;
import com.aerosphere.airline.entity.Airline;
import com.aerosphere.airline.mapper.AirlineMapper;
import com.aerosphere.airline.repository.AirlineRepository;
import com.aerosphere.exception.custom.BusinessException;
import com.aerosphere.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Purpose:
 * Implements the business operations for airline management.
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
@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;

    @Override
    public AirlineResponse createAirline(AirlineRequest request) {

        if (airlineRepository.existsByAirlineCode(
                request.getAirlineCode())) {

            throw new BusinessException(
                    "Airline code already exists.");
        }

        Airline airline =
                airlineMapper.toEntity(request);

        Airline savedAirline =
                airlineRepository.save(airline);

        return airlineMapper.toResponse(savedAirline);
    }

    @Override
    public List<AirlineResponse> getAllAirlines() {

        return airlineRepository.findAll()
                .stream()
                .map(airlineMapper::toResponse)
                .toList();
    }

    @Override
    public AirlineResponse getAirlineById(Long id) {

        Airline airline =
                airlineRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Airline not found."));

        return airlineMapper.toResponse(airline);
    }

    @Override
    public AirlineResponse updateAirline(
            Long id,
            AirlineRequest request) {

        Airline airline =
                airlineRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Airline not found."));

        if (!airline.getAirlineCode().equals(
                request.getAirlineCode())
                &&
                airlineRepository.existsByAirlineCode(
                        request.getAirlineCode())) {

            throw new BusinessException(
                    "Airline code already exists.");
        }

        airline.setAirlineCode(request.getAirlineCode());
        airline.setAirlineName(request.getAirlineName());
        airline.setCountry(request.getCountry());
        airline.setStatus(request.getStatus());

        Airline updatedAirline =
                airlineRepository.save(airline);

        return airlineMapper.toResponse(updatedAirline);
    }

    @Override
    public void deleteAirline(Long id) {

        Airline airline =
                airlineRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Airline not found."));

        airlineRepository.delete(airline);
    }

}
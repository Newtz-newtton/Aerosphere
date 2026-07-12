package com.aerosphere.flight.service;

import com.aerosphere.aircraft.entity.Aircraft;
import com.aerosphere.aircraft.repository.AircraftRepository;
import com.aerosphere.airline.entity.Airline;
import com.aerosphere.airline.repository.AirlineRepository;
import com.aerosphere.airport.entity.Airport;
import com.aerosphere.airport.repository.AirportRepository;
import com.aerosphere.exception.custom.BusinessException;
import com.aerosphere.exception.custom.ResourceNotFoundException;
import com.aerosphere.flight.dto.request.FlightRequest;
import com.aerosphere.flight.dto.response.FlightResponse;
import com.aerosphere.flight.entity.Flight;
import com.aerosphere.flight.mapper.FlightMapper;
import com.aerosphere.flight.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Purpose:
 * Implements the business operations for flight management.
 *
 * Responsibilities:
 * - Create flights.
 * - Retrieve flights.
 * - Update flights.
 * - Delete flights.
 * - Validate flight business rules.
 *
 * Module:
 * Flight
 */
@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirlineRepository airlineRepository;
    private final AircraftRepository aircraftRepository;
    private final AirportRepository airportRepository;
    private final FlightMapper flightMapper;

    @Override
    public FlightResponse createFlight(FlightRequest request) {

        if (flightRepository.existsByFlightNumber(request.getFlightNumber())) {
            throw new BusinessException("Flight number already exists.");
        }

        Airline airline = airlineRepository.findById(request.getAirlineId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Airline not found."));

        Aircraft aircraft = aircraftRepository.findById(request.getAircraftId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Aircraft not found."));

        Airport departureAirport = airportRepository.findById(request.getDepartureAirportId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Departure airport not found."));

        Airport arrivalAirport = airportRepository.findById(request.getArrivalAirportId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Arrival airport not found."));

        validateFlightRequest(request, departureAirport, arrivalAirport);

        Flight flight = Flight.builder()
                .flightNumber(request.getFlightNumber())
                .airline(airline)
                .aircraft(aircraft)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .status(request.getStatus())
                .build();

        Flight savedFlight = flightRepository.save(flight);

        return flightMapper.toResponse(savedFlight);
    }

    @Override
    public List<FlightResponse> getAllFlights() {

        return flightRepository.findAllWithRelationships()
                .stream()
                .map(flightMapper::toResponse)
                .toList();
    }

    @Override
    public FlightResponse getFlightById(Long id) {

        Flight flight = flightRepository.findByIdWithRelationships(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Flight not found."));

        return flightMapper.toResponse(flight);
    }

    @Override
    public FlightResponse updateFlight(Long id, FlightRequest request) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Flight not found."));

        if (!flight.getFlightNumber().equals(request.getFlightNumber())
                && flightRepository.existsByFlightNumber(request.getFlightNumber())) {

            throw new BusinessException("Flight number already exists.");
        }

        Airline airline = airlineRepository.findById(request.getAirlineId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Airline not found."));

        Aircraft aircraft = aircraftRepository.findById(request.getAircraftId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Aircraft not found."));

        Airport departureAirport = airportRepository.findById(request.getDepartureAirportId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Departure airport not found."));

        Airport arrivalAirport = airportRepository.findById(request.getArrivalAirportId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Arrival airport not found."));

        validateFlightRequest(request, departureAirport, arrivalAirport);

        flight.setFlightNumber(request.getFlightNumber());
        flight.setAirline(airline);
        flight.setAircraft(aircraft);
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureTime(request.getDepartureTime());
        flight.setArrivalTime(request.getArrivalTime());
        flight.setStatus(request.getStatus());

        flightRepository.save(flight);

        Flight updatedFlight = flightRepository
                .findByIdWithRelationships(flight.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Flight not found."));

        return flightMapper.toResponse(updatedFlight);
    }

    @Override
    public void deleteFlight(Long id) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Flight not found."));

        flightRepository.delete(flight);
    }

    /**
     * Validates business rules for flight creation and update.
     */
    private void validateFlightRequest(
            FlightRequest request,
            Airport departureAirport,
            Airport arrivalAirport) {

        if (departureAirport.getId().equals(arrivalAirport.getId())) {
            throw new BusinessException(
                    "Departure and arrival airports cannot be the same.");
        }

        if (!request.getArrivalTime().isAfter(request.getDepartureTime())) {
            throw new BusinessException(
                    "Arrival time must be after departure time.");
        }
    }

}
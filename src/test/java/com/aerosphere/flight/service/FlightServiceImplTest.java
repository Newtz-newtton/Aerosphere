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
import com.aerosphere.flight.entity.FlightStatus;
import com.aerosphere.flight.mapper.FlightMapper;
import com.aerosphere.flight.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @Mock
    private AircraftRepository aircraftRepository;

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private FlightMapper flightMapper;

    @InjectMocks
    private FlightServiceImpl flightService;

    @Test
    void createFlightSavesAndReturnsResponse() {

        FlightRequest request = flightRequest();

        Airline airline = airline();

        Aircraft aircraft = aircraft();

        Airport departure = departureAirport();

        Airport arrival = arrivalAirport();

        Flight flight = flight();

        FlightResponse expected = flightResponse();

        when(flightRepository.existsByFlightNumber("AI101"))
                .thenReturn(false);

        when(airlineRepository.findById(1L))
                .thenReturn(Optional.of(airline));

        when(aircraftRepository.findById(1L))
                .thenReturn(Optional.of(aircraft));

        when(airportRepository.findById(1L))
                .thenReturn(Optional.of(departure));

        when(airportRepository.findById(2L))
                .thenReturn(Optional.of(arrival));

        when(flightRepository.save(any(Flight.class)))
                .thenReturn(flight);

        when(flightMapper.toResponse(flight))
                .thenReturn(expected);

        FlightResponse response =
                flightService.createFlight(request);

        assertThat(response).isSameAs(expected);

        verify(flightRepository).save(any(Flight.class));
    }

    @Test
    void createFlightRejectsDuplicateFlightNumber() {

        when(flightRepository.existsByFlightNumber("AI101"))
                .thenReturn(true);

        assertThatThrownBy(() ->
                flightService.createFlight(flightRequest()))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Flight number already exists.");
    }

    @Test
    void getAllFlightsReturnsMappedResponses() {

        Flight flight = flight();

        FlightResponse response = flightResponse();

        when(flightRepository.findAllWithRelationships())
                .thenReturn(List.of(flight));

        when(flightMapper.toResponse(flight))
                .thenReturn(response);

        List<FlightResponse> results =
                flightService.getAllFlights();

        assertThat(results)
                .containsExactly(response);
    }

    @Test
    void getFlightByIdRejectsUnknownId() {

        when(flightRepository.findByIdWithRelationships(99L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                flightService.getFlightById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Flight not found.");
    }

    @Test
    void updateFlightUpdatesExistingFlight() {

        Flight existing = flight();

        Airline airline = airline();

        Aircraft aircraft = aircraft();

        Airport departure = departureAirport();

        Airport arrival = arrivalAirport();

        FlightResponse expected = flightResponse();

        when(flightRepository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(airlineRepository.findById(1L))
                .thenReturn(Optional.of(airline));

        when(aircraftRepository.findById(1L))
                .thenReturn(Optional.of(aircraft));

        when(airportRepository.findById(1L))
                .thenReturn(Optional.of(departure));

        when(airportRepository.findById(2L))
                .thenReturn(Optional.of(arrival));

        when(flightRepository.findByIdWithRelationships(1L))
                .thenReturn(Optional.of(existing));

        when(flightMapper.toResponse(existing))
                .thenReturn(expected);

        FlightResponse response =
                flightService.updateFlight(1L, flightRequest());

        assertThat(response)
                .isSameAs(expected);

        verify(flightRepository).save(existing);
    }

    @Test
    void deleteFlightDeletesExistingFlight() {

        Flight flight = flight();

        when(flightRepository.findById(1L))
                .thenReturn(Optional.of(flight));

        flightService.deleteFlight(1L);

        verify(flightRepository)
                .delete(flight);
    }

    @Test
    void createFlightRejectsSameDepartureAndArrivalAirport() {

        FlightRequest request = flightRequest();

        Airline airline = airline();

        Aircraft aircraft = aircraft();

        Airport airport = departureAirport();

        when(flightRepository.existsByFlightNumber("AI101"))
                .thenReturn(false);

        when(airlineRepository.findById(1L))
                .thenReturn(Optional.of(airline));

        when(aircraftRepository.findById(1L))
                .thenReturn(Optional.of(aircraft));

        when(airportRepository.findById(1L))
                .thenReturn(Optional.of(airport));

        when(airportRepository.findById(2L))
                .thenReturn(Optional.of(airport));

        assertThatThrownBy(() ->
                flightService.createFlight(request))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Departure and arrival airports cannot be the same.");
    }

    @Test
    void createFlightRejectsInvalidArrivalTime() {

        FlightRequest request = flightRequest();

        request.setArrivalTime(
                request.getDepartureTime().minusHours(1));

        Airline airline = airline();

        Aircraft aircraft = aircraft();

        Airport departure = departureAirport();

        Airport arrival = arrivalAirport();

        when(flightRepository.existsByFlightNumber("AI101"))
                .thenReturn(false);

        when(airlineRepository.findById(1L))
                .thenReturn(Optional.of(airline));

        when(aircraftRepository.findById(1L))
                .thenReturn(Optional.of(aircraft));

        when(airportRepository.findById(1L))
                .thenReturn(Optional.of(departure));

        when(airportRepository.findById(2L))
                .thenReturn(Optional.of(arrival));

        assertThatThrownBy(() ->
                flightService.createFlight(request))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Arrival time must be after departure time.");
    }

    // ---------- Helper Methods ----------

    private FlightRequest flightRequest() {

        return FlightRequest.builder()
                .flightNumber("AI101")
                .airlineId(1L)
                .aircraftId(1L)
                .departureAirportId(1L)
                .arrivalAirportId(2L)
                .departureTime(LocalDateTime.now().plusHours(2))
                .arrivalTime(LocalDateTime.now().plusHours(5))
                .status(FlightStatus.SCHEDULED)
                .build();
    }

    private Flight flight() {

        return Flight.builder()
                .id(1L)
                .flightNumber("AI101")
                .airline(airline())
                .aircraft(aircraft())
                .departureAirport(departureAirport())
                .arrivalAirport(arrivalAirport())
                .departureTime(LocalDateTime.now().plusHours(2))
                .arrivalTime(LocalDateTime.now().plusHours(5))
                .status(FlightStatus.SCHEDULED)
                .build();
    }

    private FlightResponse flightResponse() {

        return FlightResponse.builder()
                .id(1L)
                .flightNumber("AI101")
                .airlineCode("AI")
                .airlineName("Air India")
                .aircraftRegistrationNumber("VT-ABC")
                .aircraftManufacturer("Airbus")
                .aircraftModel("A320")
                .departureAirportCode("MAA")
                .departureAirportName("Chennai International Airport")
                .arrivalAirportCode("DEL")
                .arrivalAirportName("Delhi International Airport")
                .departureTime(LocalDateTime.now().plusHours(2))
                .arrivalTime(LocalDateTime.now().plusHours(5))
                .status(FlightStatus.SCHEDULED)
                .build();
    }

    private Airline airline() {

        return Airline.builder()
                .id(1L)
                .airlineCode("AI")
                .airlineName("Air India")
                .country("India")
                .build();
    }

    private Aircraft aircraft() {

        return Aircraft.builder()
                .id(1L)
                .registrationNumber("VT-ABC")
                .manufacturer("Airbus")
                .model("A320")
                .build();
    }

    private Airport departureAirport() {

        Airport airport = new Airport();

        airport.setId(1L);
        airport.setAirportCode("MAA");
        airport.setAirportName("Chennai International Airport");

        return airport;
    }

    private Airport arrivalAirport() {

        Airport airport = new Airport();

        airport.setId(2L);
        airport.setAirportCode("DEL");
        airport.setAirportName("Delhi International Airport");

        return airport;
    }

}
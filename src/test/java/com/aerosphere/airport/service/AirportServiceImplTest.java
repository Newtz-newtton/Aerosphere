package com.aerosphere.airport.service;

import com.aerosphere.airport.dto.request.AirportRequest;
import com.aerosphere.airport.dto.response.AirportResponse;
import com.aerosphere.airport.entity.Airport;
import com.aerosphere.airport.entity.AirportStatus;
import com.aerosphere.airport.mapper.AirportMapper;
import com.aerosphere.airport.repository.AirportRepository;
import com.aerosphere.exception.custom.BusinessException;
import com.aerosphere.exception.custom.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AirportServiceImplTest {

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private AirportMapper airportMapper;

    @InjectMocks
    private AirportServiceImpl airportService;

    @Test
    void createAirportSavesAndReturnsResponse() {
        AirportRequest request = airportRequest("DEL", "Indira Gandhi International Airport");
        Airport airport = airport("DEL", "Indira Gandhi International Airport");
        AirportResponse expectedResponse = airportResponse(1L, "DEL", "Indira Gandhi International Airport");

        when(airportRepository.existsByAirportCode("DEL")).thenReturn(false);
        when(airportMapper.toEntity(request)).thenReturn(airport);
        when(airportRepository.save(airport)).thenReturn(airport);
        when(airportMapper.toResponse(airport)).thenReturn(expectedResponse);

        AirportResponse response = airportService.createAirport(request);

        assertThat(response).isSameAs(expectedResponse);
        verify(airportRepository).save(airport);
    }

    @Test
    void createAirportRejectsDuplicateCode() {
        AirportRequest request = airportRequest("DEL", "Indira Gandhi International Airport");

        when(airportRepository.existsByAirportCode("DEL")).thenReturn(true);

        assertThatThrownBy(() -> airportService.createAirport(request))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Airport code already exists.");
    }

    @Test
    void getAllAirportsMapsRepositoryResults() {
        Airport firstAirport = airport("DEL", "Indira Gandhi International Airport");
        Airport secondAirport = airport("BOM", "Chhatrapati Shivaji Maharaj International Airport");
        AirportResponse firstResponse = airportResponse(1L, "DEL", "Indira Gandhi International Airport");
        AirportResponse secondResponse = airportResponse(2L, "BOM", "Chhatrapati Shivaji Maharaj International Airport");

        when(airportRepository.findAll()).thenReturn(List.of(firstAirport, secondAirport));
        when(airportMapper.toResponse(firstAirport)).thenReturn(firstResponse);
        when(airportMapper.toResponse(secondAirport)).thenReturn(secondResponse);

        List<AirportResponse> responses = airportService.getAllAirports();

        assertThat(responses).containsExactly(firstResponse, secondResponse);
    }

    @Test
    void getAirportByIdRejectsUnknownId() {
        when(airportRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> airportService.getAirportById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Airport not found.");
    }

    @Test
    void updateAirportSavesChangedValues() {
        Airport existingAirport = airport("DEL", "Indira Gandhi International Airport");
        AirportRequest request = airportRequest("BOM", "Chhatrapati Shivaji Maharaj International Airport");
        AirportResponse expectedResponse = airportResponse(1L, "BOM", "Chhatrapati Shivaji Maharaj International Airport");

        when(airportRepository.findById(1L)).thenReturn(Optional.of(existingAirport));
        when(airportRepository.existsByAirportCode("BOM")).thenReturn(false);
        when(airportRepository.save(existingAirport)).thenReturn(existingAirport);
        when(airportMapper.toResponse(existingAirport)).thenReturn(expectedResponse);

        AirportResponse response = airportService.updateAirport(1L, request);

        assertThat(response).isSameAs(expectedResponse);
        assertThat(existingAirport.getAirportCode()).isEqualTo("BOM");
        assertThat(existingAirport.getAirportName())
                .isEqualTo("Chhatrapati Shivaji Maharaj International Airport");
        verify(airportRepository).save(existingAirport);
    }

    @Test
    void updateAirportRejectsCodeUsedByAnotherAirport() {
        Airport existingAirport = airport("DEL", "Indira Gandhi International Airport");
        AirportRequest request = airportRequest("BOM", "Chhatrapati Shivaji Maharaj International Airport");

        when(airportRepository.findById(1L)).thenReturn(Optional.of(existingAirport));
        when(airportRepository.existsByAirportCode("BOM")).thenReturn(true);

        assertThatThrownBy(() -> airportService.updateAirport(1L, request))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Airport code already exists.");
    }

    @Test
    void deleteAirportDeletesExistingAirport() {
        Airport airport = airport("DEL", "Indira Gandhi International Airport");

        when(airportRepository.findById(1L)).thenReturn(Optional.of(airport));

        airportService.deleteAirport(1L);

        verify(airportRepository).delete(airport);
    }

    private AirportRequest airportRequest(String code, String name) {
        return AirportRequest.builder()
                .airportCode(code)
                .airportName(name)
                .city("New Delhi")
                .country("India")
                .timezone("Asia/Kolkata")
                .status(AirportStatus.ACTIVE)
                .build();
    }

    private Airport airport(String code, String name) {
        return Airport.builder()
                .airportCode(code)
                .airportName(name)
                .city("New Delhi")
                .country("India")
                .timezone("Asia/Kolkata")
                .status(AirportStatus.ACTIVE)
                .build();
    }

    private AirportResponse airportResponse(Long id, String code, String name) {
        return AirportResponse.builder()
                .id(id)
                .airportCode(code)
                .airportName(name)
                .city("New Delhi")
                .country("India")
                .timezone("Asia/Kolkata")
                .status(AirportStatus.ACTIVE)
                .build();
    }
}

package com.aerosphere.airline.service;

import com.aerosphere.airline.dto.request.AirlineRequest;
import com.aerosphere.airline.dto.response.AirlineResponse;
import com.aerosphere.airline.entity.Airline;
import com.aerosphere.airline.entity.AirlineStatus;
import com.aerosphere.airline.mapper.AirlineMapper;
import com.aerosphere.airline.repository.AirlineRepository;
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
class AirlineServiceImplTest {

    @Mock
    private AirlineRepository airlineRepository;

    @Mock
    private AirlineMapper airlineMapper;

    @InjectMocks
    private AirlineServiceImpl airlineService;

    @Test
    void createAirlineSavesAndReturnsResponse() {
        AirlineRequest request = airlineRequest("6E", "IndiGo");
        Airline airline = airline("6E", "IndiGo");
        AirlineResponse expectedResponse = airlineResponse(1L, "6E", "IndiGo");

        when(airlineRepository.existsByAirlineCode("6E")).thenReturn(false);
        when(airlineMapper.toEntity(request)).thenReturn(airline);
        when(airlineRepository.save(airline)).thenReturn(airline);
        when(airlineMapper.toResponse(airline)).thenReturn(expectedResponse);

        AirlineResponse response = airlineService.createAirline(request);

        assertThat(response).isSameAs(expectedResponse);
        verify(airlineRepository).save(airline);
    }

    @Test
    void createAirlineRejectsDuplicateCode() {
        AirlineRequest request = airlineRequest("6E", "IndiGo");

        when(airlineRepository.existsByAirlineCode("6E")).thenReturn(true);

        assertThatThrownBy(() -> airlineService.createAirline(request))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Airline code already exists.");
    }

    @Test
    void getAllAirlinesMapsRepositoryResults() {
        Airline firstAirline = airline("6E", "IndiGo");
        Airline secondAirline = airline("AI", "Air India");
        AirlineResponse firstResponse = airlineResponse(1L, "6E", "IndiGo");
        AirlineResponse secondResponse = airlineResponse(2L, "AI", "Air India");

        when(airlineRepository.findAll()).thenReturn(List.of(firstAirline, secondAirline));
        when(airlineMapper.toResponse(firstAirline)).thenReturn(firstResponse);
        when(airlineMapper.toResponse(secondAirline)).thenReturn(secondResponse);

        List<AirlineResponse> responses = airlineService.getAllAirlines();

        assertThat(responses).containsExactly(firstResponse, secondResponse);
    }

    @Test
    void getAirlineByIdRejectsUnknownId() {
        when(airlineRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> airlineService.getAirlineById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Airline not found.");
    }

    @Test
    void updateAirlineSavesChangedValues() {
        Airline existingAirline = airline("6E", "IndiGo");
        AirlineRequest request = airlineRequest("AI", "Air India");
        AirlineResponse expectedResponse = airlineResponse(1L, "AI", "Air India");

        when(airlineRepository.findById(1L)).thenReturn(Optional.of(existingAirline));
        when(airlineRepository.existsByAirlineCode("AI")).thenReturn(false);
        when(airlineRepository.save(existingAirline)).thenReturn(existingAirline);
        when(airlineMapper.toResponse(existingAirline)).thenReturn(expectedResponse);

        AirlineResponse response = airlineService.updateAirline(1L, request);

        assertThat(response).isSameAs(expectedResponse);
        assertThat(existingAirline.getAirlineCode()).isEqualTo("AI");
        assertThat(existingAirline.getAirlineName()).isEqualTo("Air India");
        verify(airlineRepository).save(existingAirline);
    }

    @Test
    void updateAirlineRejectsCodeUsedByAnotherAirline() {
        Airline existingAirline = airline("6E", "IndiGo");
        AirlineRequest request = airlineRequest("AI", "Air India");

        when(airlineRepository.findById(1L)).thenReturn(Optional.of(existingAirline));
        when(airlineRepository.existsByAirlineCode("AI")).thenReturn(true);

        assertThatThrownBy(() -> airlineService.updateAirline(1L, request))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Airline code already exists.");
    }

    @Test
    void deleteAirlineDeletesExistingAirline() {
        Airline airline = airline("6E", "IndiGo");

        when(airlineRepository.findById(1L)).thenReturn(Optional.of(airline));

        airlineService.deleteAirline(1L);

        verify(airlineRepository).delete(airline);
    }

    private AirlineRequest airlineRequest(String code, String name) {
        return AirlineRequest.builder()
                .airlineCode(code)
                .airlineName(name)
                .country("India")
                .status(AirlineStatus.ACTIVE)
                .build();
    }

    private Airline airline(String code, String name) {
        return Airline.builder()
                .airlineCode(code)
                .airlineName(name)
                .country("India")
                .status(AirlineStatus.ACTIVE)
                .build();
    }

    private AirlineResponse airlineResponse(Long id, String code, String name) {
        return AirlineResponse.builder()
                .id(id)
                .airlineCode(code)
                .airlineName(name)
                .country("India")
                .status(AirlineStatus.ACTIVE)
                .build();
    }
}

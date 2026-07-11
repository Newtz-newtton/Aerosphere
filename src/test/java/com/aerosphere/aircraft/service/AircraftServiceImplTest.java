package com.aerosphere.aircraft.service;

import com.aerosphere.aircraft.dto.request.AircraftRequest;
import com.aerosphere.aircraft.dto.response.AircraftResponse;
import com.aerosphere.aircraft.entity.Aircraft;
import com.aerosphere.aircraft.entity.AircraftStatus;
import com.aerosphere.aircraft.mapper.AircraftMapper;
import com.aerosphere.aircraft.repository.AircraftRepository;
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
class AircraftServiceImplTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @Mock
    private AircraftMapper aircraftMapper;

    @InjectMocks
    private AircraftServiceImpl aircraftService;

    @Test
    void createAircraftSavesAndReturnsResponse() {

        AircraftRequest request =
                aircraftRequest("VT-ABC", "Airbus", "A320neo");

        Aircraft aircraft =
                aircraft("VT-ABC", "Airbus", "A320neo");

        AircraftResponse expectedResponse =
                aircraftResponse(1L, "VT-ABC", "Airbus", "A320neo");

        when(aircraftRepository.existsByRegistrationNumber("VT-ABC"))
                .thenReturn(false);

        when(aircraftMapper.toEntity(request))
                .thenReturn(aircraft);

        when(aircraftRepository.save(aircraft))
                .thenReturn(aircraft);

        when(aircraftMapper.toResponse(aircraft))
                .thenReturn(expectedResponse);

        AircraftResponse response =
                aircraftService.createAircraft(request);

        assertThat(response).isSameAs(expectedResponse);

        verify(aircraftRepository).save(aircraft);
    }

    @Test
    void createAircraftRejectsDuplicateRegistrationNumber() {

        AircraftRequest request =
                aircraftRequest("VT-ABC", "Airbus", "A320neo");

        when(aircraftRepository.existsByRegistrationNumber("VT-ABC"))
                .thenReturn(true);

        assertThatThrownBy(() ->
                aircraftService.createAircraft(request))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Registration number already exists.");
    }

    @Test
    void getAllAircraftMapsRepositoryResults() {

        Aircraft firstAircraft =
                aircraft("VT-ABC", "Airbus", "A320neo");

        Aircraft secondAircraft =
                aircraft("VT-XYZ", "Boeing", "737-800");

        AircraftResponse firstResponse =
                aircraftResponse(1L, "VT-ABC", "Airbus", "A320neo");

        AircraftResponse secondResponse =
                aircraftResponse(2L, "VT-XYZ", "Boeing", "737-800");

        when(aircraftRepository.findAll())
                .thenReturn(List.of(firstAircraft, secondAircraft));

        when(aircraftMapper.toResponse(firstAircraft))
                .thenReturn(firstResponse);

        when(aircraftMapper.toResponse(secondAircraft))
                .thenReturn(secondResponse);

        List<AircraftResponse> responses =
                aircraftService.getAllAircraft();

        assertThat(responses)
                .containsExactly(firstResponse, secondResponse);
    }

    @Test
    void getAircraftByIdRejectsUnknownId() {

        when(aircraftRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                aircraftService.getAircraftById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Aircraft not found.");
    }

    @Test
    void updateAircraftSavesChangedValues() {

        Aircraft existingAircraft =
                aircraft("VT-ABC", "Airbus", "A320neo");

        AircraftRequest request =
                aircraftRequest("VT-XYZ", "Boeing", "737 MAX 8");

        AircraftResponse expectedResponse =
                aircraftResponse(1L, "VT-XYZ", "Boeing", "737 MAX 8");

        when(aircraftRepository.findById(1L))
                .thenReturn(Optional.of(existingAircraft));

        when(aircraftRepository.existsByRegistrationNumber("VT-XYZ"))
                .thenReturn(false);

        when(aircraftRepository.save(existingAircraft))
                .thenReturn(existingAircraft);

        when(aircraftMapper.toResponse(existingAircraft))
                .thenReturn(expectedResponse);

        AircraftResponse response =
                aircraftService.updateAircraft(1L, request);

        assertThat(response).isSameAs(expectedResponse);

        assertThat(existingAircraft.getRegistrationNumber())
                .isEqualTo("VT-XYZ");

        assertThat(existingAircraft.getManufacturer())
                .isEqualTo("Boeing");

        assertThat(existingAircraft.getModel())
                .isEqualTo("737 MAX 8");

        verify(aircraftRepository).save(existingAircraft);
    }

    @Test
    void updateAircraftRejectsDuplicateRegistrationNumber() {

        Aircraft existingAircraft =
                aircraft("VT-ABC", "Airbus", "A320neo");

        AircraftRequest request =
                aircraftRequest("VT-XYZ", "Boeing", "737 MAX 8");

        when(aircraftRepository.findById(1L))
                .thenReturn(Optional.of(existingAircraft));

        when(aircraftRepository.existsByRegistrationNumber("VT-XYZ"))
                .thenReturn(true);

        assertThatThrownBy(() ->
                aircraftService.updateAircraft(1L, request))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Registration number already exists.");
    }

    @Test
    void deleteAircraftDeletesExistingAircraft() {

        Aircraft aircraft =
                aircraft("VT-ABC", "Airbus", "A320neo");

        when(aircraftRepository.findById(1L))
                .thenReturn(Optional.of(aircraft));

        aircraftService.deleteAircraft(1L);

        verify(aircraftRepository).delete(aircraft);
    }

    private AircraftRequest aircraftRequest(
            String registrationNumber,
            String manufacturer,
            String model) {

        return AircraftRequest.builder()
                .registrationNumber(registrationNumber)
                .manufacturer(manufacturer)
                .model(model)
                .seatingCapacity(186)
                .status(AircraftStatus.ACTIVE)
                .build();
    }

    private Aircraft aircraft(
            String registrationNumber,
            String manufacturer,
            String model) {

        return Aircraft.builder()
                .registrationNumber(registrationNumber)
                .manufacturer(manufacturer)
                .model(model)
                .seatingCapacity(186)
                .status(AircraftStatus.ACTIVE)
                .build();
    }

    private AircraftResponse aircraftResponse(
            Long id,
            String registrationNumber,
            String manufacturer,
            String model) {

        return AircraftResponse.builder()
                .id(id)
                .registrationNumber(registrationNumber)
                .manufacturer(manufacturer)
                .model(model)
                .seatingCapacity(186)
                .status(AircraftStatus.ACTIVE)
                .build();
    }

}
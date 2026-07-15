package com.aerosphere.checkin.service;

import com.aerosphere.checkin.dto.request.CheckInRequest;
import com.aerosphere.checkin.dto.response.CheckInResponse;
import com.aerosphere.checkin.entity.CheckIn;
import com.aerosphere.checkin.entity.CheckInStatus;
import com.aerosphere.checkin.mapper.CheckInMapper;
import com.aerosphere.checkin.repository.CheckInRepository;
import com.aerosphere.exception.custom.BusinessException;
import com.aerosphere.exception.custom.ResourceNotFoundException;
import com.aerosphere.ticket.entity.Ticket;
import com.aerosphere.ticket.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Purpose:
 * Unit tests for CheckInServiceImpl.
 *
 * Responsibilities:
 * - Verify business logic.
 * - Verify repository interactions.
 * - Verify exception handling.
 *
 * Module:
 * Check-in
 */
@ExtendWith(MockitoExtension.class)
class CheckInServiceImplTest {

    @Mock
    private CheckInRepository checkInRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private CheckInMapper checkInMapper;

    @InjectMocks
    private CheckInServiceImpl checkInService;

    private Ticket ticket;
    private CheckIn checkIn;
    private CheckInRequest request;
    private CheckInResponse response;

    @BeforeEach
    void setUp() {

        ticket = Ticket.builder()
                .id(1L)
                .ticketNumber("AS-TKT-20260715-ABC123")
                .passengerName("John Doe")
                .flightNumber("AI101")
                .build();

        checkIn = CheckIn.builder()
                .id(1L)
                .ticket(ticket)
                .passengerName(ticket.getPassengerName())
                .flightNumber(ticket.getFlightNumber())
                .checkInStatus(CheckInStatus.CHECKED_IN)
                .build();

        request = CheckInRequest.builder()
                .ticketId(1L)
                .build();

        response = CheckInResponse.builder()
                .id(1L)
                .ticketId(1L)
                .ticketNumber(ticket.getTicketNumber())
                .passengerName(ticket.getPassengerName())
                .flightNumber(ticket.getFlightNumber())
                .checkInStatus(CheckInStatus.CHECKED_IN)
                .build();
    }

    @Test
    void shouldCreateCheckInSuccessfully() {

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(ticket));

        when(checkInRepository.existsByTicketId(1L))
                .thenReturn(false);

        when(checkInRepository.save(ArgumentMatchers.any(CheckIn.class)))
                .thenReturn(checkIn);

        when(checkInRepository.findByIdWithRelationships(1L))
                .thenReturn(Optional.of(checkIn));

        when(checkInMapper.toResponse(checkIn))
                .thenReturn(response);

        CheckInResponse result =
                checkInService.createCheckIn(request);

        assertNotNull(result);
        assertEquals("AS-TKT-20260715-ABC123",
                result.getTicketNumber());

        verify(ticketRepository).findById(1L);
        verify(checkInRepository).existsByTicketId(1L);
        verify(checkInRepository).save(any(CheckIn.class));
        verify(checkInRepository)
                .findByIdWithRelationships(1L);
        verify(checkInMapper).toResponse(checkIn);
    }

    @Test
    void shouldThrowExceptionWhenTicketNotFound() {

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> checkInService.createCheckIn(request));

        verify(ticketRepository).findById(1L);

        verify(checkInRepository, never())
                .save(any(CheckIn.class));
    }

    @Test
    void shouldThrowExceptionWhenDuplicateCheckInExists() {

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(ticket));

        when(checkInRepository.existsByTicketId(1L))
                .thenReturn(true);

        assertThrows(
                BusinessException.class,
                () -> checkInService.createCheckIn(request));

        verify(checkInRepository, never())
                .save(any(CheckIn.class));
    }

    @Test
    void shouldReturnAllCheckIns() {

        when(checkInRepository.findAllWithRelationships())
                .thenReturn(List.of(checkIn));

        when(checkInMapper.toResponse(checkIn))
                .thenReturn(response);

        List<CheckInResponse> result =
                checkInService.getAllCheckIns();

        assertEquals(1, result.size());

        verify(checkInRepository)
                .findAllWithRelationships();

        verify(checkInMapper)
                .toResponse(checkIn);
    }

    @Test
    void shouldReturnCheckInById() {

        when(checkInRepository.findByIdWithRelationships(1L))
                .thenReturn(Optional.of(checkIn));

        when(checkInMapper.toResponse(checkIn))
                .thenReturn(response);

        CheckInResponse result =
                checkInService.getCheckInById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(checkInRepository)
                .findByIdWithRelationships(1L);

        verify(checkInMapper)
                .toResponse(checkIn);
    }

    @Test
    void shouldThrowExceptionWhenCheckInNotFound() {

        when(checkInRepository.findByIdWithRelationships(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> checkInService.getCheckInById(1L));

        verify(checkInRepository)
                .findByIdWithRelationships(1L);
    }

    @Test
    void shouldUpdateCheckInSuccessfully() {

        when(checkInRepository.findById(1L))
                .thenReturn(Optional.of(checkIn));

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(ticket));

        when(checkInRepository.save(any(CheckIn.class)))
                .thenReturn(checkIn);

        when(checkInRepository.findByIdWithRelationships(1L))
                .thenReturn(Optional.of(checkIn));

        when(checkInMapper.toResponse(checkIn))
                .thenReturn(response);

        CheckInResponse result =
                checkInService.updateCheckIn(1L, request);

        assertNotNull(result);
        assertEquals(ticket.getPassengerName(),
                result.getPassengerName());

        verify(checkInRepository)
                .save(checkIn);

        verify(checkInMapper)
                .toResponse(checkIn);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingMissingCheckIn() {

        when(checkInRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> checkInService.updateCheckIn(1L, request));

        verify(checkInRepository, never())
                .save(any(CheckIn.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingMissingTicket() {

        when(checkInRepository.findById(1L))
                .thenReturn(Optional.of(checkIn));

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> checkInService.updateCheckIn(1L, request));

        verify(checkInRepository, never())
                .save(any(CheckIn.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingToDuplicateTicket() {

        Ticket anotherTicket = Ticket.builder()
                .id(2L)
                .ticketNumber("AS-TKT-20260715-XYZ789")
                .passengerName("Jane Smith")
                .flightNumber("AI202")
                .build();

        CheckIn existingCheckIn = CheckIn.builder()
                .id(1L)
                .ticket(ticket)
                .build();

        CheckInRequest updateRequest = CheckInRequest.builder()
                .ticketId(2L)
                .build();

        when(checkInRepository.findById(1L))
                .thenReturn(Optional.of(existingCheckIn));

        when(ticketRepository.findById(2L))
                .thenReturn(Optional.of(anotherTicket));

        when(checkInRepository.existsByTicketId(2L))
                .thenReturn(true);

        assertThrows(
                BusinessException.class,
                () -> checkInService.updateCheckIn(
                        1L,
                        updateRequest));

        verify(checkInRepository, never())
                .save(any(CheckIn.class));
    }

    @Test
    void shouldDeleteCheckInSuccessfully() {

        when(checkInRepository.findById(1L))
                .thenReturn(Optional.of(checkIn));

        doNothing().when(checkInRepository)
                .delete(checkIn);

        checkInService.deleteCheckIn(1L);

        verify(checkInRepository)
                .delete(checkIn);
    }

    @Test
    void shouldThrowExceptionWhenDeletingMissingCheckIn() {

        when(checkInRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> checkInService.deleteCheckIn(1L));

        verify(checkInRepository, never())
                .delete(any(CheckIn.class));
    }
}


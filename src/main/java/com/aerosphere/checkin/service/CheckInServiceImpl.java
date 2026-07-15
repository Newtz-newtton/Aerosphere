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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Purpose:
 * Implements business operations for passenger check-in management.
 *
 * Responsibilities:
 * - Create check-ins.
 * - Retrieve check-ins.
 * - Update check-ins.
 * - Delete check-ins.
 * - Validate check-in business rules.
 *
 * Module:
 * Check-in
 */
@Service
@RequiredArgsConstructor
public class CheckInServiceImpl implements CheckInService {

    private final CheckInRepository checkInRepository;
    private final TicketRepository ticketRepository;
    private final CheckInMapper checkInMapper;

    @Override
    public CheckInResponse createCheckIn(CheckInRequest request) {

        Ticket ticket = ticketRepository.findById(request.getTicketId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket not found."));

        validateCheckInRequest(ticket);

        CheckIn checkIn = CheckIn.builder()
                .ticket(ticket)
                .passengerName(ticket.getPassengerName())
                .flightNumber(ticket.getFlightNumber())
                .checkInStatus(CheckInStatus.CHECKED_IN)
                .checkedInAt(LocalDateTime.now())
                .build();

        CheckIn savedCheckIn = checkInRepository.save(checkIn);

        CheckIn createdCheckIn = checkInRepository
                .findByIdWithRelationships(savedCheckIn.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Check-in not found."));

        return checkInMapper.toResponse(createdCheckIn);
    }

    @Override
    public List<CheckInResponse> getAllCheckIns() {

        return checkInRepository.findAllWithRelationships()
                .stream()
                .map(checkInMapper::toResponse)
                .toList();
    }

    @Override
    public CheckInResponse getCheckInById(Long id) {

        CheckIn checkIn = checkInRepository
                .findByIdWithRelationships(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Check-in not found."));

        return checkInMapper.toResponse(checkIn);
    }

    @Override
    public CheckInResponse updateCheckIn(Long id,
                                         CheckInRequest request) {

        CheckIn checkIn = checkInRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Check-in not found."));

        Ticket ticket = ticketRepository.findById(request.getTicketId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket not found."));

        /*
         * Prevent assigning a ticket that already has
         * another check-in.
         */
        if (!checkIn.getTicket().getId().equals(ticket.getId())
                && checkInRepository.existsByTicketId(ticket.getId())) {

            throw new BusinessException(
                    "Check-in already exists for this ticket.");
        }

        checkIn.setTicket(ticket);

        /*
         * Refresh immutable snapshots from the
         * newly associated ticket.
         */
        checkIn.setPassengerName(ticket.getPassengerName());

        checkIn.setFlightNumber(ticket.getFlightNumber());

        /*
         * checkedInAt intentionally remains unchanged.
         * It represents the original check-in event.
         */

        checkInRepository.save(checkIn);

        CheckIn updatedCheckIn = checkInRepository
                .findByIdWithRelationships(checkIn.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Check-in not found."));

        return checkInMapper.toResponse(updatedCheckIn);
    }

    @Override
    public void deleteCheckIn(Long id) {

        CheckIn checkIn = checkInRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Check-in not found."));

        checkInRepository.delete(checkIn);
    }

    /**
     * Validates check-in business rules.
     */
    private void validateCheckInRequest(Ticket ticket) {

        if (checkInRepository.existsByTicketId(ticket.getId())) {

            throw new BusinessException(
                    "Check-in already exists for this ticket.");
        }
    }

}
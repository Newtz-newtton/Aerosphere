package com.aerosphere.ticket.service;

import com.aerosphere.booking.entity.Booking;
import com.aerosphere.booking.repository.BookingRepository;
import com.aerosphere.common.util.ReferenceGenerator;
import com.aerosphere.exception.custom.BusinessException;
import com.aerosphere.exception.custom.ResourceNotFoundException;
import com.aerosphere.payment.entity.Payment;
import com.aerosphere.payment.entity.PaymentStatus;
import com.aerosphere.payment.repository.PaymentRepository;
import com.aerosphere.ticket.dto.request.TicketRequest;
import com.aerosphere.ticket.dto.response.TicketResponse;
import com.aerosphere.ticket.entity.Ticket;
import com.aerosphere.ticket.entity.TicketStatus;
import com.aerosphere.ticket.mapper.TicketMapper;
import com.aerosphere.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Purpose:
 * Implements business operations for ticket management.
 *
 * Responsibilities:
 * - Generate tickets.
 * - Retrieve tickets.
 * - Update tickets.
 * - Delete tickets.
 * - Validate ticket business rules.
 *
 * Module:
 * Ticket
 */
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final TicketMapper ticketMapper;
    private final ReferenceGenerator referenceGenerator;

    @Override
    public TicketResponse createTicket(TicketRequest request) {

        Booking booking = bookingRepository
                .findByIdWithUserAndFlight(request.getBookingId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found."));

        Payment payment = paymentRepository
                .findByBookingId(booking.getId())
                .orElseThrow(() ->
                        new BusinessException(
                                "Payment not found for this booking."));

        validateTicketRequest(booking, payment);

        String passengerName =
                booking.getUser().getFirstName()
                        + " "
                        + booking.getUser().getLastName();

        Ticket ticket = Ticket.builder()
                .ticketNumber(
                        referenceGenerator.generateTicketNumber())
                .booking(booking)
                .payment(payment)
                .passengerName(passengerName)
                .flightNumber(
                        booking.getFlight().getFlightNumber())
                .ticketStatus(TicketStatus.GENERATED)
                .issuedAt(LocalDateTime.now())
                .build();

        Ticket savedTicket = ticketRepository.save(ticket);

        return ticketMapper.toResponse(savedTicket);
    }

    @Override
    public List<TicketResponse> getAllTickets() {

        return ticketRepository.findAllWithRelationships()
                .stream()
                .map(ticketMapper::toResponse)
                .toList();
    }

    @Override
    public TicketResponse getTicketById(Long id) {

        Ticket ticket = ticketRepository
                .findByIdWithRelationships(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket not found."));

        return ticketMapper.toResponse(ticket);
    }

    @Override
    public TicketResponse updateTicket(Long id,
                                       TicketRequest request) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket not found."));

        Booking booking = bookingRepository
                .findByIdWithUserAndFlight(request.getBookingId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found."));

        Payment payment = paymentRepository.findByBookingId(booking.getId())
                .orElseThrow(() ->
                        new BusinessException(
                                "Payment not found for this booking."));

        if (payment.getPaymentStatus() != PaymentStatus.SUCCESS) {
            throw new BusinessException(
                    "Ticket can only be generated for successful payments.");
        }
        ticket.setBooking(booking);

        ticket.setPayment(payment);

        ticket.setPassengerName(
                booking.getUser().getFirstName()
                        + " "
                        + booking.getUser().getLastName());

        ticket.setFlightNumber(
                booking.getFlight().getFlightNumber());

        ticketRepository.save(ticket);

        Ticket updatedTicket = ticketRepository
                .findByIdWithRelationships(ticket.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket not found."));

        return ticketMapper.toResponse(updatedTicket);
    }

    @Override
    public void deleteTicket(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket not found."));

        ticketRepository.delete(ticket);
    }

    /**
     * Validates ticket business rules.
     */
    private void validateTicketRequest(Booking booking,
                                       Payment payment) {

        if (payment.getPaymentStatus() != PaymentStatus.SUCCESS) {
            throw new BusinessException(
                    "Ticket can only be generated for successful payments.");
        }

        if (ticketRepository.findByBookingId(booking.getId()).isPresent()) {
            throw new BusinessException(
                    "Ticket already exists for this booking.");
        }
    }

}
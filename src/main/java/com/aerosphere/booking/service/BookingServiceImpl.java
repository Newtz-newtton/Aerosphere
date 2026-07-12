package com.aerosphere.booking.service;

import com.aerosphere.auth.entity.User;
import com.aerosphere.auth.repository.UserRepository;
import com.aerosphere.booking.dto.request.BookingRequest;
import com.aerosphere.booking.dto.response.BookingResponse;
import com.aerosphere.booking.entity.Booking;
import com.aerosphere.booking.entity.BookingStatus;
import com.aerosphere.booking.mapper.BookingMapper;
import com.aerosphere.booking.repository.BookingRepository;
import com.aerosphere.common.util.ReferenceGenerator;
import com.aerosphere.exception.custom.BusinessException;
import com.aerosphere.exception.custom.ResourceNotFoundException;
import com.aerosphere.flight.entity.Flight;
import com.aerosphere.flight.entity.FlightStatus;
import com.aerosphere.flight.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Purpose:
 * Implements business operations for booking management.
 *
 * Responsibilities:
 * - Create bookings.
 * - Retrieve bookings.
 * - Update bookings.
 * - Delete bookings.
 * - Validate booking business rules.
 *
 * Module:
 * Booking
 */
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final BookingMapper bookingMapper;
    private final ReferenceGenerator referenceGenerator;

    @Override
    public BookingResponse createBooking(BookingRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Flight not found."));

        validateBookingRequest(flight);

        Booking booking = Booking.builder()
                .bookingReference(referenceGenerator.generateBookingReference())
                .user(user)
                .flight(flight)
                .bookingStatus(BookingStatus.PENDING_PAYMENT)
                .totalFare(request.getTotalFare())
                .bookingDate(LocalDateTime.now())
                .build();

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toResponse(savedBooking);
    }

    @Override
    public List<BookingResponse> getAllBookings() {

        return bookingRepository.findAllWithRelationships()
                .stream()
                .map(bookingMapper::toResponse)
                .toList();
    }

    @Override
    public BookingResponse getBookingById(Long id) {

        Booking booking = bookingRepository.findByIdWithRelationships(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found."));

        return bookingMapper.toResponse(booking);
    }

    @Override
    public BookingResponse updateBooking(Long id,
                                         BookingRequest request) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found."));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Flight not found."));

        validateBookingRequest(flight);

        booking.setUser(user);
        booking.setFlight(flight);
        booking.setTotalFare(request.getTotalFare());

        bookingRepository.save(booking);

        Booking updatedBooking = bookingRepository
                .findByIdWithRelationships(booking.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found."));

        return bookingMapper.toResponse(updatedBooking);
    }

    @Override
    public void deleteBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found."));

        bookingRepository.delete(booking);
    }

    /**
     * Validates booking business rules.
     */
    private void validateBookingRequest(Flight flight) {

        if (flight.getStatus() == FlightStatus.CANCELLED) {
            throw new BusinessException(
                    "Booking cannot be created for a cancelled flight.");
        }
    }

}
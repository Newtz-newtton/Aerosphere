package com.aerosphere.booking.service;

import com.aerosphere.auth.entity.Role;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private ReferenceGenerator referenceGenerator;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    void createBookingCreatesBookingSuccessfully() {

        BookingRequest request = bookingRequest();

        User user = user();

        Flight flight = flight();

        Booking booking = booking();

        BookingResponse expected = bookingResponse();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(flightRepository.findById(1L))
                .thenReturn(Optional.of(flight));

        when(referenceGenerator.generateBookingReference())
                .thenReturn("AS-BKG-20260712-ABC123");

        when(bookingRepository.save(org.mockito.ArgumentMatchers.any(Booking.class)))
                .thenReturn(booking);

        when(bookingMapper.toResponse(booking))
                .thenReturn(expected);

        BookingResponse response =
                bookingService.createBooking(request);

        assertThat(response)
                .isSameAs(expected);

        verify(bookingRepository)
                .save(org.mockito.ArgumentMatchers.any(Booking.class));
    }

    @Test
    void createBookingRejectsUnknownUser() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                bookingService.createBooking(bookingRequest()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("User not found.");
    }

    @Test
    void createBookingRejectsUnknownFlight() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user()));

        when(flightRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                bookingService.createBooking(bookingRequest()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Flight not found.");
    }

    @Test
    void createBookingRejectsCancelledFlight() {

        Flight cancelledFlight = flight();

        cancelledFlight.setStatus(FlightStatus.CANCELLED);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user()));

        when(flightRepository.findById(1L))
                .thenReturn(Optional.of(cancelledFlight));

        assertThatThrownBy(() ->
                bookingService.createBooking(bookingRequest()))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Booking cannot be created for a cancelled flight.");
    }

    @Test
    void getAllBookingsMapsRepositoryResults() {

        Booking booking = booking();

        BookingResponse expected = bookingResponse();

        when(bookingRepository.findAllWithRelationships())
                .thenReturn(List.of(booking));

        when(bookingMapper.toResponse(booking))
                .thenReturn(expected);

        List<BookingResponse> response =
                bookingService.getAllBookings();

        assertThat(response)
                .containsExactly(expected);
    }

    @Test
    void getBookingByIdRejectsUnknownId() {

        when(bookingRepository.findByIdWithRelationships(99L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                bookingService.getBookingById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Booking not found.");
    }

    @Test
    void updateBookingUpdatesExistingBooking() {

        Booking existing = booking();

        User user = user();

        Flight flight = flight();

        BookingResponse expected = bookingResponse();

        when(bookingRepository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(flightRepository.findById(1L))
                .thenReturn(Optional.of(flight));

        when(bookingRepository.save(existing))
                .thenReturn(existing);

        when(bookingRepository.findByIdWithRelationships(1L))
                .thenReturn(Optional.of(existing));

        when(bookingMapper.toResponse(existing))
                .thenReturn(expected);

        BookingResponse response =
                bookingService.updateBooking(1L, bookingRequest());

        assertThat(response)
                .isSameAs(expected);

        verify(bookingRepository).save(existing);
    }

    @Test
    void updateBookingRejectsCancelledFlight() {

        Booking existing = booking();

        Flight cancelledFlight = flight();

        cancelledFlight.setStatus(FlightStatus.CANCELLED);

        when(bookingRepository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user()));

        when(flightRepository.findById(1L))
                .thenReturn(Optional.of(cancelledFlight));

        assertThatThrownBy(() ->
                bookingService.updateBooking(1L, bookingRequest()))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Booking cannot be created for a cancelled flight.");
    }

    @Test
    void deleteBookingDeletesExistingBooking() {

        Booking booking = booking();

        when(bookingRepository.findById(1L))
                .thenReturn(Optional.of(booking));

        bookingService.deleteBooking(1L);

        verify(bookingRepository).delete(booking);
    }

    private BookingRequest bookingRequest() {

        return BookingRequest.builder()
                .userId(1L)
                .flightId(1L)
                .totalFare(BigDecimal.valueOf(12500))
                .build();
    }

    private Booking booking() {

        return Booking.builder()
                .id(1L)
                .bookingReference("AS-BKG-20260712-ABC123")
                .user(user())
                .flight(flight())
                .bookingStatus(BookingStatus.PENDING_PAYMENT)
                .totalFare(BigDecimal.valueOf(12500))
                .bookingDate(LocalDateTime.now())
                .build();
    }

    private BookingResponse bookingResponse() {

        return BookingResponse.builder()
                .id(1L)
                .bookingReference("AS-BKG-20260712-ABC123")
                .userId(1L)
                .customerName("John Doe")
                .flightId(1L)
                .flightNumber("AI101")
                .bookingStatus(BookingStatus.PENDING_PAYMENT)
                .totalFare(BigDecimal.valueOf(12500))
                .bookingDate(LocalDateTime.now())
                .build();
    }

    private User user() {

        return User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("password")
                .role(Role.CUSTOMER)
                .build();
    }

    private Flight flight() {

        return Flight.builder()
                .id(1L)
                .flightNumber("AI101")
                .departureTime(LocalDateTime.now().plusHours(2))
                .arrivalTime(LocalDateTime.now().plusHours(5))
                .status(FlightStatus.SCHEDULED)
                .build();
    }

}
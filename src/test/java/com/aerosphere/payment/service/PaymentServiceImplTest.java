package com.aerosphere.payment.service;

import com.aerosphere.booking.entity.Booking;
import com.aerosphere.booking.repository.BookingRepository;
import com.aerosphere.common.util.ReferenceGenerator;
import com.aerosphere.exception.custom.BusinessException;
import com.aerosphere.exception.custom.ResourceNotFoundException;
import com.aerosphere.payment.dto.request.PaymentRequest;
import com.aerosphere.payment.dto.response.PaymentResponse;
import com.aerosphere.payment.entity.Payment;
import com.aerosphere.payment.entity.PaymentMethod;
import com.aerosphere.payment.entity.PaymentStatus;
import com.aerosphere.payment.mapper.PaymentMapper;
import com.aerosphere.payment.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Purpose:
 * Unit tests for PaymentServiceImpl.
 *
 * Responsibilities:
 * - Verify payment business logic.
 * - Validate business rules.
 * - Ensure repository interactions.
 *
 * Module:
 * Payment
 */
@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private PaymentMapper paymentMapper;

    @Mock
    private ReferenceGenerator referenceGenerator;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Booking booking;
    private Payment payment;
    private PaymentRequest paymentRequest;
    private PaymentResponse paymentResponse;

    @BeforeEach
    void setUp() {

        booking = Booking.builder()
                .id(1L)
                .bookingReference("AS-BKG-20260712-ABC123")
                .totalFare(BigDecimal.valueOf(15000))
                .build();

        payment = Payment.builder()
                .id(1L)
                .paymentReference("AS-PAY-20260712-XYZ123")
                .booking(booking)
                .paymentMethod(PaymentMethod.UPI)
                .paymentStatus(PaymentStatus.SUCCESS)
                .amount(BigDecimal.valueOf(15000))
                .paymentDate(LocalDateTime.now())
                .build();

        paymentRequest = PaymentRequest.builder()
                .bookingId(1L)
                .paymentMethod(PaymentMethod.UPI)
                .build();

        paymentResponse = PaymentResponse.builder()
                .id(1L)
                .paymentReference("AS-PAY-20260712-XYZ123")
                .bookingId(1L)
                .bookingReference("AS-BKG-20260712-ABC123")
                .paymentMethod(PaymentMethod.UPI)
                .paymentStatus(PaymentStatus.SUCCESS)
                .amount(BigDecimal.valueOf(15000))
                .paymentDate(LocalDateTime.now())
                .build();
    }

    @Test
    void createPayment_ShouldCreatePaymentSuccessfully() {

        when(bookingRepository.findById(1L))
                .thenReturn(Optional.of(booking));

        when(paymentRepository.existsByBookingId(1L))
                .thenReturn(false);

        when(referenceGenerator.generatePaymentReference())
                .thenReturn("AS-PAY-20260712-XYZ123");

        when(paymentRepository.save(any(Payment.class)))
                .thenReturn(payment);

        when(paymentMapper.toResponse(payment))
                .thenReturn(paymentResponse);

        PaymentResponse response =
                paymentService.createPayment(paymentRequest);

        assertNotNull(response);
        assertEquals("AS-PAY-20260712-XYZ123",
                response.getPaymentReference());

        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
    void createPayment_ShouldThrowException_WhenBookingNotFound() {

        when(bookingRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> paymentService.createPayment(paymentRequest));
    }

    @Test
    void createPayment_ShouldThrowException_WhenPaymentAlreadyExists() {

        when(bookingRepository.findById(1L))
                .thenReturn(Optional.of(booking));

        when(paymentRepository.existsByBookingId(1L))
                .thenReturn(true);

        assertThrows(BusinessException.class,
                () -> paymentService.createPayment(paymentRequest));
    }

    @Test
    void getAllPayments_ShouldReturnPayments() {

        when(paymentRepository.findAllWithRelationships())
                .thenReturn(List.of(payment));

        when(paymentMapper.toResponse(payment))
                .thenReturn(paymentResponse);

        List<PaymentResponse> response =
                paymentService.getAllPayments();

        assertEquals(1, response.size());

        verify(paymentRepository)
                .findAllWithRelationships();
    }

    @Test
    void getPaymentById_ShouldReturnPayment() {

        when(paymentRepository.findByIdWithRelationships(1L))
                .thenReturn(Optional.of(payment));

        when(paymentMapper.toResponse(payment))
                .thenReturn(paymentResponse);

        PaymentResponse response =
                paymentService.getPaymentById(1L);

        assertEquals(1L, response.getId());
    }

    @Test
    void getPaymentById_ShouldThrowException_WhenPaymentNotFound() {

        when(paymentRepository.findByIdWithRelationships(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> paymentService.getPaymentById(1L));
    }

    @Test
    void updatePayment_ShouldUpdatePaymentMethodSuccessfully() {

        Payment updatedPayment = Payment.builder()
                .id(1L)
                .paymentReference("AS-PAY-20260712-XYZ123")
                .booking(booking)
                .paymentMethod(PaymentMethod.DEBIT_CARD)
                .paymentStatus(PaymentStatus.SUCCESS)
                .amount(BigDecimal.valueOf(15000))
                .paymentDate(LocalDateTime.now())
                .build();

        PaymentResponse updatedResponse = PaymentResponse.builder()
                .id(1L)
                .paymentReference("AS-PAY-20260712-XYZ123")
                .bookingId(1L)
                .bookingReference("AS-BKG-20260712-ABC123")
                .paymentMethod(PaymentMethod.DEBIT_CARD)
                .paymentStatus(PaymentStatus.SUCCESS)
                .amount(BigDecimal.valueOf(15000))
                .paymentDate(LocalDateTime.now())
                .build();

        PaymentRequest updateRequest = PaymentRequest.builder()
                .bookingId(1L)
                .paymentMethod(PaymentMethod.DEBIT_CARD)
                .build();

        when(paymentRepository.findById(1L))
                .thenReturn(Optional.of(payment));

        when(paymentRepository.save(any(Payment.class)))
                .thenReturn(updatedPayment);

        when(paymentRepository.findByIdWithRelationships(1L))
                .thenReturn(Optional.of(updatedPayment));

        when(paymentMapper.toResponse(updatedPayment))
                .thenReturn(updatedResponse);

        PaymentResponse response =
                paymentService.updatePayment(1L, updateRequest);

        assertNotNull(response);
        assertEquals(PaymentMethod.DEBIT_CARD,
                response.getPaymentMethod());

        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
    void deletePayment_ShouldDeletePaymentSuccessfully() {

        when(paymentRepository.findById(1L))
                .thenReturn(Optional.of(payment));

        paymentService.deletePayment(1L);

        verify(paymentRepository).delete(payment);
    }

    @Test
    void deletePayment_ShouldThrowException_WhenPaymentNotFound() {

        when(paymentRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> paymentService.deletePayment(1L));
    }

}
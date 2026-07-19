package com.aerosphere.payment.service;

import com.aerosphere.booking.entity.Booking;
import com.aerosphere.booking.repository.BookingRepository;
import com.aerosphere.common.util.ReferenceGenerator;
import com.aerosphere.exception.custom.BusinessException;
import com.aerosphere.exception.custom.ResourceNotFoundException;
import com.aerosphere.kafka.constant.KafkaConstants;
import com.aerosphere.kafka.event.payment.PaymentCompletedEvent;
import com.aerosphere.kafka.mapper.PaymentEventMapper;
import com.aerosphere.kafka.publisher.KafkaEventPublisher;
import com.aerosphere.payment.dto.request.PaymentRequest;
import com.aerosphere.payment.dto.response.PaymentResponse;
import com.aerosphere.payment.entity.Payment;
import com.aerosphere.payment.entity.PaymentStatus;
import com.aerosphere.payment.mapper.PaymentMapper;
import com.aerosphere.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Purpose:
 * Implements business operations for payment management.
 *
 * Responsibilities:
 * - Create payments.
 * - Retrieve payments.
 * - Update payments.
 * - Delete payments.
 * - Validate payment business rules.
 * - Publish payment completion events.
 *
 * Module:
 * Payment
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentMapper paymentMapper;
    private final ReferenceGenerator referenceGenerator;

    private final PaymentEventMapper paymentEventMapper;
    private final KafkaEventPublisher kafkaEventPublisher;

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {

        Booking booking = bookingRepository
                .findByIdWithUserAndFlight(request.getBookingId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found."));

        validatePaymentRequest(booking);

        Payment payment = Payment.builder()
                .paymentReference(
                        referenceGenerator.generatePaymentReference())
                .booking(booking)
                .paymentMethod(request.getPaymentMethod())
                .paymentStatus(PaymentStatus.SUCCESS)
                .amount(booking.getTotalFare())
                .paymentDate(LocalDateTime.now())
                .build();

        Payment savedPayment = paymentRepository.save(payment);

        PaymentCompletedEvent event =
                paymentEventMapper.toPaymentCompletedEvent(savedPayment);

        kafkaEventPublisher.publish(
                KafkaConstants.PAYMENT_EVENTS_TOPIC,
                event
        );

        return paymentMapper.toResponse(savedPayment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {

        return paymentRepository.findAllWithRelationships()
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {

        Payment payment = paymentRepository
                .findByIdWithRelationships(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment not found."));

        return paymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponse updatePayment(Long id,
                                         PaymentRequest request) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment not found."));

        payment.setPaymentMethod(request.getPaymentMethod());

        paymentRepository.save(payment);

        Payment updatedPayment = paymentRepository
                .findByIdWithRelationships(payment.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment not found."));

        return paymentMapper.toResponse(updatedPayment);
    }

    @Override
    public void deletePayment(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment not found."));

        paymentRepository.delete(payment);
    }

    /**
     * Validates payment business rules.
     */
    private void validatePaymentRequest(Booking booking) {

        if (paymentRepository.existsByBookingId(booking.getId())) {
            throw new BusinessException(
                    "Payment already exists for this booking.");
        }
    }

}
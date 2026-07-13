package com.aerosphere.payment.service;

import com.aerosphere.payment.dto.request.PaymentRequest;
import com.aerosphere.payment.dto.response.PaymentResponse;

import java.util.List;

/**
 * Purpose:
 * Defines business operations for payment management.
 *
 * Responsibilities:
 * - Create payments.
 * - Retrieve payments.
 * - Update payments.
 * - Delete payments.
 *
 * Module:
 * Payment
 */
public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest request);

    List<PaymentResponse> getAllPayments();

    PaymentResponse getPaymentById(Long id);

    PaymentResponse updatePayment(Long id,
                                  PaymentRequest request);

    void deletePayment(Long id);

}
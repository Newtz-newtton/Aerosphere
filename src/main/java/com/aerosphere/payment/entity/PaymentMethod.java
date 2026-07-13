package com.aerosphere.payment.entity;

/**
 * Purpose:
 * Represents the supported payment methods.
 *
 * Responsibilities:
 * - Standardize payment options.
 * - Support future payment gateway integration.
 * - Eliminate hardcoded payment methods.
 *
 * Module:
 * Payment
 */
public enum PaymentMethod {

    CREDIT_CARD,

    DEBIT_CARD,

    UPI,

    NET_BANKING

}
package com.aerosphere.payment.entity;

/**
 * Purpose:
 * Represents the current processing state of a payment.
 *
 * Responsibilities:
 * - Track payment lifecycle.
 * - Support payment workflow validation.
 * - Eliminate hardcoded payment statuses.
 *
 * Module:
 * Payment
 */
public enum PaymentStatus {

    PENDING,

    SUCCESS,

    FAILED

}
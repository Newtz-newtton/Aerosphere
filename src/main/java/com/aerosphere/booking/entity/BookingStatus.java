package com.aerosphere.booking.entity;

/**
 * Purpose:
 * Represents the lifecycle states of a booking.
 *
 * Responsibilities:
 * - Defines valid booking states.
 * - Supports booking workflow validation.
 * - Prevents invalid business transitions.
 *
 * Module:
 * Booking
 */
public enum BookingStatus {

    PENDING_PAYMENT,

    CONFIRMED,

    CANCELLED,

    EXPIRED,

    COMPLETED

}
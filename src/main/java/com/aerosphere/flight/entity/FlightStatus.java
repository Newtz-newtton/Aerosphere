package com.aerosphere.flight.entity;

/**
 * Purpose:
 * Represents the operational status of a flight.
 *
 * Responsibilities:
 * - Track the lifecycle of a flight.
 * - Support operational monitoring.
 * - Control booking and check-in workflows.
 *
 * Module:
 * Flight
 */
public enum FlightStatus {

    SCHEDULED,

    BOARDING,

    DEPARTED,

    ARRIVED,

    CANCELLED

}
package com.aerosphere.aircraft.entity;

/**
 * Purpose:
 * Represents the operational status of an aircraft.
 *
 * Responsibilities:
 * - Indicate whether an aircraft is operational.
 * - Support aircraft maintenance tracking.
 * - Prevent retired aircraft from future scheduling.
 *
 * Module:
 * Aircraft
 */
public enum AircraftStatus {

    ACTIVE,

    MAINTENANCE,

    RETIRED

}
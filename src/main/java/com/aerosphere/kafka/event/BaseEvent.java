package com.aerosphere.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.UUID;

/**
 * Purpose:
 * Base class for all Kafka events in the AeroSphere event platform.
 *
 * Responsibilities:
 * - Provide common metadata for all domain events.
 * - Standardize the event structure across the application.
 * - Support event identification, tracing, and versioning.
 *
 * Module:
 * Kafka Event Platform
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseEvent {


    private UUID eventId;
    private String eventType;

    private String eventVersion;

    private Instant timestamp;

    private String source;
}
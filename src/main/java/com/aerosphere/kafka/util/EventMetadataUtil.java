package com.aerosphere.kafka.util;

import com.aerosphere.kafka.event.BaseEvent;

import java.time.Instant;
import java.util.UUID;

/**
 * Purpose:
 * Populates common metadata for Kafka events.
 *
 * Responsibilities:
 * - Generate unique event identifiers.
 * - Populate event metadata.
 * - Standardize event information across the platform.
 *
 * Module:
 * Kafka
 */
public final class EventMetadataUtil {

    private static final String EVENT_VERSION = "1.0";

    private EventMetadataUtil() {
    }

    public static void populate(BaseEvent event, String source) {

        event.setEventId(UUID.randomUUID());
        event.setEventType(event.getClass().getSimpleName());
        event.setEventVersion(EVENT_VERSION);
        event.setTimestamp(Instant.now());
        event.setSource(source);

    }

}
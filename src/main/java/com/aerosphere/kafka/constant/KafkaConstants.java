package com.aerosphere.kafka.constant;

/**
 * Purpose:
 * Centralizes Kafka constants used by the AeroSphere event platform.
 *
 * Responsibilities:
 * - Store Kafka topic names.
 * - Store Kafka default configuration values.
 * - Store common event metadata constants.
 *
 * Module:
 * Kafka Event Platform
 */
public final class KafkaConstants {

    /**
     * Kafka Topics
     */
    public static final String PAYMENT_EVENTS_TOPIC = "payment-events";

    public static final String DEFAULT_EVENT_VERSION = "1.0";


    public static final int DEFAULT_PARTITIONS = 1;

    public static final short DEFAULT_REPLICATION_FACTOR = 1;

    private KafkaConstants() {
        throw new IllegalStateException("Utility class");
    }
}
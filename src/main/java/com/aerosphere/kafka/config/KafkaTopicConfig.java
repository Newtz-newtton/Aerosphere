package com.aerosphere.kafka.config;

import com.aerosphere.kafka.constant.KafkaConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * Purpose:
 * Configures Kafka topics used by the AeroSphere event platform.
 *
 * Responsibilities:
 * - Register Kafka business topics during application startup.
 *
 * Module:
 * Kafka Event Platform
 */
@Configuration
public class KafkaTopicConfig {

    @Bean
    public KafkaAdmin.NewTopics aeroSphereTopics() {

        return new KafkaAdmin.NewTopics(

                TopicBuilder
                        .name(KafkaConstants.PAYMENT_EVENTS_TOPIC)
                        .partitions(KafkaConstants.DEFAULT_PARTITIONS)
                        .replicas(KafkaConstants.DEFAULT_REPLICATION_FACTOR)
                        .build()
        );
    }
}
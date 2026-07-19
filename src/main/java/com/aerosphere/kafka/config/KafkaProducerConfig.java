package com.aerosphere.kafka.config;

import com.aerosphere.kafka.event.BaseEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Purpose:
 * Configures Kafka producer infrastructure for publishing events.
 *
 * Responsibilities:
 * - Configure Kafka producer properties.
 * - Expose ProducerFactory bean.
 * - Expose KafkaTemplate bean.
 *
 * Module:
 * Kafka Event Platform
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, BaseEvent> producerFactory() {

        Map<String, Object> configs = new HashMap<>();

        configs.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers
        );

        configs.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );

        configs.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class
        );

        configs.put(
                ProducerConfig.ACKS_CONFIG,
                "all"
        );

        configs.put(
                ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
                true
        );

        configs.put(
                ProducerConfig.RETRIES_CONFIG,
                Integer.MAX_VALUE
        );

        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, BaseEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
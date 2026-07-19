package com.aerosphere.kafka.config;

import com.aerosphere.kafka.event.payment.PaymentCompletedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Purpose:
 * Configures Kafka consumer infrastructure for consuming events.
 *
 * Responsibilities:
 * - Configure Kafka consumer properties.
 * - Expose ConsumerFactory bean.
 * - Expose KafkaListenerContainerFactory bean.
 *
 * Module:
 * Kafka Event Platform
 */
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, PaymentCompletedEvent> consumerFactory() {

        Map<String, Object> configs = new HashMap<>();

        configs.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers
        );

        configs.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId
        );

        configs.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );

        configs.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class
        );

        configs.put(
                JsonDeserializer.TRUSTED_PACKAGES,
                "com.aerosphere.kafka.event.*"
        );

        configs.put(
                JsonDeserializer.VALUE_DEFAULT_TYPE,
                PaymentCompletedEvent.class
        );

        configs.put(
                JsonDeserializer.USE_TYPE_INFO_HEADERS,
                false
        );

        return new DefaultKafkaConsumerFactory<>(
                configs,
                new StringDeserializer(),
                new JsonDeserializer<>(PaymentCompletedEvent.class)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentCompletedEvent>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, PaymentCompletedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
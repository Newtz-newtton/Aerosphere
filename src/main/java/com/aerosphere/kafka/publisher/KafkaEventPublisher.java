package com.aerosphere.kafka.publisher;

import com.aerosphere.kafka.event.BaseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Publishes business events to Kafka topics.
 *
 * Responsibilities:
 * - Publish events to Kafka.
 * - Centralize Kafka publishing logic.
 * - Provide consistent logging for event publication.
 *
 * Module:
 * Kafka
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventPublisher {

    private final KafkaTemplate<String, BaseEvent> kafkaTemplate;

    public void publish(String topic, BaseEvent event) {

        kafkaTemplate.send(topic, event);

        log.info(
                "Published event [{}] to topic [{}]",
                event.getEventType(),
                topic
        );
    }

}
# Phase 08 – Kafka Event-Driven Integration

## Objective

Introduce asynchronous event-driven communication between business modules using Apache Kafka while preserving the modular monolith architecture.

---

# Architecture

Payment Module
│
▼
PaymentCompletedEvent
│
▼
Kafka Producer
│
▼
payment-events Topic
│
▼
PaymentCompletedConsumer
│
▼
NotificationService
│
▼
Logging / Email / SMS Provider

---

# Components

## Kafka Infrastructure

- KafkaProducerConfig
- KafkaConsumerConfig
- KafkaTopicConfig
- KafkaConstants
- EventMetadataUtil
- KafkaEventPublisher

---

## Events

### BaseEvent

Shared metadata:

- eventId
- eventType
- eventVersion
- timestamp
- source

### PaymentCompletedEvent

Business Payload:

- paymentId
- paymentReference
- bookingId
- bookingReference
- recipient
- paymentMethod
- paymentStatus
- amount
- paymentDate

---

## Mapper

PaymentEventMapper

Responsibilities:

- Convert Payment entity to PaymentCompletedEvent
- Populate common metadata

---

## Producer

PaymentService

After successful payment persistence:

1. Map Payment → PaymentCompletedEvent
2. Publish event using KafkaEventPublisher

---

## Consumer

PaymentCompletedConsumer

Responsibilities:

- Consume PaymentCompletedEvent
- Build NotificationRequest
- Invoke NotificationService

No business logic is implemented inside the consumer.

---

## Topic

payment-events

Partitions : 1

Replication Factor : 1

---

## Event Flow

REST API

↓

PaymentService

↓

Database

↓

PaymentCompletedEvent

↓

Kafka Producer

↓

Kafka Broker

↓

PaymentCompletedConsumer

↓

NotificationService

↓

Notification Provider

↓

Notification Database

---

## Testing

Verified:

✅ Kafka broker connectivity

✅ Topic creation

✅ Producer publishing

✅ Consumer consumption

✅ Notification creation

✅ Logging provider execution

✅ Notification persisted with SENT status

---

## Challenges

Issue:

LazyInitializationException while accessing Booking.user.email inside PaymentEventMapper.

Root Cause:

Booking loaded using JpaRepository.findById() which does not fetch lazy relationships.

Resolution:

Replaced

findById()

with

findByIdWithUserAndFlight()

which eagerly fetches User and Flight.

---

## Future Improvements

- Transactional Outbox Pattern
- Retry Policy
- Dead Letter Topics
- Email Provider
- SMS Provider
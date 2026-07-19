# CHANGELOG

All notable changes to the AeroSphere project are documented in this file.

This project follows a milestone-based versioning strategy, where each completed project phase represents a new release.

---

# Version History

| Version | Release | Status | Date |
|----------|---------|--------|------------|
| v0.1.0 | Project Foundation | ✅ Released | 2026-07-09 |
| v0.2.0 | Core Infrastructure | ✅ Released | 2026-07-10 |
| v0.3.0 | Authentication Module | ✅ Released | 2026-07-10 |

----------------------------------------------------------------------------

# v0.1.0

## Release Name

Project Foundation

## Date

2026-07-09

### Added

- Spring Boot 3 project
- Java 21 configuration
- Maven Wrapper
- Git repository initialization
- GitHub repository
- Initial project package structure
- PostgreSQL project configuration
- Application configuration
- Initial project documentation

### Changed

- None

### Fixed

- None

### Documentation

- Phase 1 Documentation completed

### Git Milestone

100a0c6

----------------------------------------------------------------------------

# v0.2.0

## Release Name

Core Infrastructure

## Date

2026-07-10

### Added

- Common module
- ApiResponse generic wrapper
- Global Exception Handler
- BusinessException
- ResourceNotFoundException
- UnauthorizedException
- Swagger / OpenAPI configuration
- Spring Security base configuration
- Common package structure

### Changed

- Standardized REST API response format
- Centralized exception handling

### Fixed

- Replaced default Spring Boot error responses
- Standardized error messages across the application

### Documentation

- Phase 2 Documentation completed

### Git Milestone

00d49cd

----------------------------------------------------------------------------

# v0.3.0

## Release Name

Authentication Module

## Date

2026-07-10

### Added

#### Domain

- User Entity
- Role Enum

#### Persistence

- User Repository

#### Security

- BCrypt Password Encoding
- JWT Service
- JWT Authentication Filter
- JWT Authentication Entry Point
- Stateless Authentication
- JWT Secret Configuration

#### Service Layer

- Authentication Service
- Authentication Service Implementation

#### Controllers

- Authentication Controller

#### DTO

- RegisterRequest
- RegisterResponse
- LoginRequest
- LoginResponse

#### APIs

- User Registration API
- User Login API

#### Validation

- Duplicate Email Validation
- Invalid Login Validation

#### Testing

- Swagger API Testing
- PostgreSQL Integration Testing
- JWT Generation Testing

### Changed

- Migrated application authentication from default Spring Security to JWT authentication.
- Configured stateless session management.
- Configured public and protected endpoint strategy.

### Fixed

- Duplicate email registration handling.
- Invalid login response handling.
- Consistent authentication error responses.

### Security

- Passwords encrypted using BCrypt.
- JWT generated after successful authentication.
- Authentication protected using Spring Security.
- Default user role assigned during registration.

### Verification

Successfully verified:

- User Registration
- User Login
- JWT Generation
- BCrypt Password Encryption
- PostgreSQL Data Persistence
- Duplicate Email Validation
- Invalid Password Validation
- Invalid Email Validation
- Swagger Integration
- Global Exception Handling

### Documentation

- Phase 3 Documentation completed.
- Master Project Status updated.

### Git Milestone

ae8e84f

----------------------------------------------------------------------------

# Current Project Status

Current Version

v0.3.0

Latest Release

Authentication Module

Current Development Phase

Phase 4 — Airport Module

Project Status

In Active Development

----------------------------------------------------------------------------

# Upcoming Release

v0.4.0

Airport Module

Planned Features

- Airport Entity
- Airport Repository
- Airport Service
- Airport Controller
- CRUD APIs
- Validation
- Swagger Testing
- PostgreSQL Testing
- Documentation
- Git Milestone

----------------------------------------------------------------------------
# Version History

---

## v0.4.0 - Airport Module Complete

### Added

- Implemented Airport module following the standardized project architecture.
- Added Airport entity with BaseEntity inheritance.
- Added AirportStatus enum.
- Added AirportRepository.
- Added AirportMapper.
- Added AirportService and AirportServiceImpl.
- Added AirportController with complete CRUD operations.
- Added AirportRequest and AirportResponse DTOs.
- Integrated Airport module with JWT authentication.
- Added Swagger JWT security support.

### Improved

- Added global validation handling using MethodArgumentNotValidException.
- Standardized validation error responses.
- Improved Swagger testing workflow using JWT authorization.

### Verified

- Airport CRUD operations.
- JWT protected endpoints.
- Bean Validation.
- PostgreSQL persistence.
- Global exception handling.
- Swagger integration.

--------------------------------------------------------------
## v0.5.0 - Airline Module Complete

### Added
- Implemented Airline module.
- Added Airline entity and AirlineStatus enum.
- Added AirlineRepository.
- Added AirlineRequest and AirlineResponse DTOs.
- Added AirlineMapper.
- Added AirlineService and AirlineServiceImpl.
- Added AirlineController.
- Added secured CRUD APIs.

### Improved
- Reused standardized architecture and exception handling.
- Verified JWT protection and Swagger integration.

### Verified
- CRUD operations.
- Bean Validation.
- Duplicate airline code validation.
- Invalid airline handling.
- PostgreSQL persistence.

--------------------------------------------------------
## v0.6.0 - Aircraft Module Complete

### Added
- Aircraft module
- AircraftServiceImplTest
- Role-based authorization using @PreAuthorize

### Verified
- CRUD
- Validation
- PostgreSQL
- Unit testing

-----------------------------------------------
# v0.7.0 - Flight Module Complete

## Phase

Flight Module

## Added

-   Flight entity and status
-   Flight repository
-   Flight request/response DTOs
-   Flight mapper
-   Flight service and implementation
-   Flight controller
-   Aggregate mapper pattern
-   Business validation pattern
-   JOIN FETCH repository pattern
-   Flight CRUD APIs
-   Flight unit tests

## Fixed

-   LazyInitializationException by introducing JOIN FETCH repository
    methods.
-   Update response mapping after save by reloading relationship graph.

## Tested

-   CRUD APIs
-   Business validations
-   Bean validation
-   PostgreSQL verification
-   Unit tests (8/8 passed)

## Status

Phase 7 Completed.

----------------------------------------------------
## v0.8.1 - Booking Module (Sprint 1)

### Added
- Implemented Booking module.
- Added Booking entity and BookingStatus.
- Added BookingRepository.
- Added BookingRequest and BookingResponse DTOs.
- Added BookingMapper.
- Added BookingService and BookingServiceImpl.
- Added BookingController.
- Integrated ReferenceGenerator.
- Added secured CRUD APIs.
- Added unit tests (9/9).

### Improved
- Implemented JOIN FETCH strategy for relationship loading.
- Standardized business validation using BusinessException.
- Reused frozen controller, mapper and service patterns.

### Verified
- CRUD operations.
- Bean Validation.
- Business rule validation.
- JWT protection.
- PostgreSQL persistence.
- Unit test suite.

Phase 8 .1 completed
---------------------------------------------

# CHANGELOG - Phase 8 Sprint 2 (Payment Module)

## Version
v0.8.2

**Sprint:** Phase 8 – Sprint 2
**Module:** Payment
**Status:** Completed, Verified and Frozen

---

## Added

### Payment Domain
- Implemented Payment entity.
- Added PaymentStatus enum.
- Added PaymentMethod enum.
- Established One-to-One Booking → Payment relationship.
- Added unique payment reference generation (AS-PAY).

### Repository Layer
- Added PaymentRepository.
- Added payment reference lookup methods.
- Added duplicate payment validation support.
- Added Booking-based lookup methods.
- Added JOIN FETCH repository methods to eliminate LazyInitializationException while preserving LAZY loading.

### Service Layer
- Implemented PaymentService.
- Implemented PaymentServiceImpl.
- Added business validation for booking existence.
- Added duplicate payment prevention.
- Derived payment amount from Booking.totalFare.
- Restricted updates to paymentMethod only.
- Reserved event publishing point for future Kafka integration.

### API Layer
- Added PaymentRequest.
- Added PaymentResponse.
- Added PaymentMapper.
- Added PaymentController.
- Integrated standardized ApiResponse wrapper.
- Applied JWT role-based authorization.

### Shared Components
- Extended ReferenceGenerator with payment reference generation.
- Reused GlobalExceptionHandler.
- Reused BusinessException and ResourceNotFoundException.

---

## Architectural Decisions Frozen

- Booking is the financial source of truth.
- One Booking can have only one Payment in V1.
- Client cannot submit payment amount.
- Payment reference generated internally.
- Payment status initialized as SUCCESS for V1.
- Payment amount, booking, reference and payment date are immutable.
- JOIN FETCH adopted as the repository standard for workflow modules requiring relationship mapping.
- Kafka integration deferred; event publishing point reserved after successful persistence.

---

## Validation Completed

### Application
- Spring Boot startup verified.
- Hibernate entity mapping verified.
- Payments table generated successfully.

### API Testing
- 12/12 Swagger API tests passed.
- CRUD operations verified.
- Security authorization verified.
- Duplicate payment prevention verified.
- Business validation verified.
- Error handling verified.

### Unit Testing
- 9/9 service-layer unit tests passed.
- Repository interactions verified.
- Business rules verified.
- Exception scenarios verified.

---

## Fixed During Sprint

- Prevented duplicate payments through service-layer validation.
- Corrected update workflow by removing duplicate-payment validation from update operations.
- Applied JOIN FETCH strategy to avoid LazyInitializationException.
- Verified role-based authorization behavior for ADMIN, STAFF and CUSTOMER.

---

## Project Status

Payment module is production-ready within the V1 architecture and fully aligned with the frozen AeroSphere standards. No architectural deviations were introduced during Sprint 2.

Next milestone: Phase 8 – Sprint 3 (Ticket Module).
--------------------------------------------------------------------
# Phase 8 -- Sprint 3 Changelog

## Added

-   Ticket module package structure.
-   Ticket entity and TicketStatus.
-   TicketRepository.
-   TicketRequest and TicketResponse.
-   TicketMapper.
-   TicketService and TicketServiceImpl.
-   TicketController.
-   Ticket reference generation (`AS-TKT`).

## Enhanced

-   Added `flightNumber` snapshot to Ticket.
-   Added BookingRepository.findByIdWithUserAndFlight().
-   Added PaymentRepository.findByBookingId().
-   Enhanced TicketRepository JOIN FETCH strategy.

## Business Rules

-   Booking must exist.
-   Payment must exist.
-   Payment must be SUCCESS.
-   Prevent duplicate ticket generation.
-   Capture passenger and flight snapshots.

## Bug Fixes

-   Fixed LazyInitializationException during ticket creation.
-   Fixed LazyInitializationException during ticket update.
-   Improved eager fetching without changing business logic.

## Verification

-   Application started successfully.
-   Swagger endpoints verified.
-   Hibernate schema validated.
-   **14/14 Ticket API tests passed.**

## Frozen

-   Ticket entity.
-   Ticket relationships.
-   Repository contracts.
-   DTO contracts.
-   Mapper responsibilities.
-   Service orchestration.
-   Controller pattern.
-   Business rules.
-   Security rules.
---------------------------------------

## Phase 8 -- Sprint 4 -- Check-in Module

### Sprint Status

-   Status: Completed
-   Architecture: Frozen
-   API Testing: 15/15 Passed
-   Unit Testing: 12/12 Passed

------------------------------------------------------------------------

## New Module

-   Introduced `checkin` module.

### Packages Added

-   controller
-   dto/request
-   dto/response
-   entity
-   mapper
-   repository
-   service

### Classes Added

-   CheckIn
-   CheckInStatus
-   CheckInRequest
-   CheckInResponse
-   CheckInMapper
-   CheckInRepository
-   CheckInService
-   CheckInServiceImpl
-   CheckInController
-   CheckInServiceImplTest

------------------------------------------------------------------------

## Database

### Table Added

-   `check_ins`

### Relationship Added

    Ticket (1)
          │
          ▼
    CheckIn (1)

### Entity Fields

-   id
-   ticket
-   passengerName
-   flightNumber
-   checkInStatus
-   checkedInAt

------------------------------------------------------------------------

## Business Rules

-   Ticket required before check-in.
-   One Check-in per Ticket.
-   Passenger name copied from Ticket.
-   Flight number copied from Ticket.
-   `CHECKED_IN` assigned during creation.
-   `checkedInAt` is immutable.
-   Ticket is the source of truth.

------------------------------------------------------------------------

## Repository Enhancements

-   existsByTicketId()
-   findByTicketId()
-   findAllWithRelationships()
-   findByIdWithRelationships()
-   JOIN FETCH used to prevent LazyInitializationException.

------------------------------------------------------------------------

## Controller

Endpoints: - POST /api/v1/checkins - GET /api/v1/checkins - GET
/api/v1/checkins/{id} - PUT /api/v1/checkins/{id} - DELETE
/api/v1/checkins/{id}

Security: - ADMIN: CRUD - STAFF: Create / Read / Update - CUSTOMER: Read

------------------------------------------------------------------------

## Integration Testing (15/15)

1.  Create Check-in
2.  Invalid Ticket ID
3.  Duplicate Check-in
4.  Get All
5.  Get By ID
6.  Invalid ID
7.  Update
8.  Invalid Ticket Update
9.  Duplicate Ticket Update
10. Delete (ADMIN)
11. Delete (STAFF -\> 403)
12. Invalid Delete
13. Validation
14. Response Mapping
15. Security

------------------------------------------------------------------------

## Unit Testing (12/12)

-   createCheckIn(): 3
-   getAllCheckIns(): 1
-   getCheckInById(): 2
-   updateCheckIn(): 4
-   deleteCheckIn(): 2

------------------------------------------------------------------------

## Freeze Summary

-   Design frozen
-   Relationships frozen
-   DTO contracts frozen
-   Repository contracts frozen
-   Service contracts frozen
-   Controller contracts frozen
-   Business rules frozen
-   Security model frozen
-   Testing completed

------------------------------------------------

# Phase 8 -- Sprint 5 (Notification Module)

## Summary

Implemented the complete Notification module and integrated it into the
AeroSphere modular monolith.

## Added

-   Notification module package
-   Notification entity
-   NotificationStatus enum
-   NotificationType enum
-   ProviderType enum
-   NotificationRepository
-   NotificationRequest
-   NotificationResponse
-   NotificationMapper
-   NotificationValidationUtil
-   NotificationProvider interface
-   LoggingNotificationProvider
-   EmailNotificationProvider (stub)
-   SmsNotificationProvider (stub)
-   NotificationProviderFactory
-   NotificationService
-   NotificationServiceImpl
-   NotificationController

## Architectural Decisions

-   Strategy Pattern for providers
-   Factory Pattern for provider resolution
-   Immutable notification history
-   No Update/Delete APIs
-   Generic recipient with provider-specific validation
-   Future Kafka compatibility retained
-   Future Redis compatibility retained

## Validation

-   Validation extracted into NotificationValidationUtil
-   Email validation
-   E.164 phone validation

## Testing

Integration Tests: 15/15 PASSED Unit Tests: -
NotificationServiceImplTest: 10/10 PASSED -
NotificationValidationUtilTest: 6/6 PASSED

## Issues Resolved

-   sent_at nullable persistence issue
-   compile/import issues
-   validation architecture refactor
--------------------------------------------------------
# CHANGELOG - Phase 08 Kafka Event-Driven Integration

------------------------------------------------------------------------

# v0.9.0 - Kafka Event-Driven Integration Complete

## Added

-   Implemented Apache Kafka integration.
-   Added KafkaProducerConfig.
-   Added KafkaConsumerConfig.
-   Added KafkaTopicConfig.
-   Added KafkaConstants.
-   Added BaseEvent for common event metadata.
-   Added PaymentCompletedEvent.
-   Added EventMetadataUtil.
-   Added KafkaEventPublisher.
-   Added PaymentEventMapper.
-   Added PaymentCompletedConsumer.
-   Integrated PaymentService with Kafka producer.
-   Integrated NotificationService through Kafka consumer.
-   Added `payment-events` topic.
-   Added event metadata population.
-   Added asynchronous Payment → Notification workflow.

## Improved

-   Decoupled Payment and Notification modules using event-driven
    communication.
-   Centralized Kafka publishing through KafkaEventPublisher.
-   Centralized event metadata generation through EventMetadataUtil.
-   Standardized event model using BaseEvent.
-   Simplified Kafka package by removing unused event flows.
-   Simplified topic configuration to a single business topic.
-   Enhanced `PaymentCompletedEvent` payload with:
    -   Booking Reference
    -   Payment Reference
    -   Recipient
-   Optimized Booking retrieval using eager relationship fetching for
    Kafka event mapping.
-   Reused the existing NotificationService without modifying business
    logic.

## Fixed

-   Resolved `LazyInitializationException` while accessing
    `Booking.user.email` during event mapping.
-   Updated PaymentService to use eager relationship fetching for Kafka
    event creation.
-   Corrected Kafka consumer package configuration.
-   Corrected trusted package configuration for JSON deserialization.

## Verified

-   Spring Boot application startup.
-   Kafka broker connectivity.
-   Kafka topic creation.
-   Kafka producer event publishing.
-   Kafka consumer event consumption.
-   PaymentCompletedEvent serialization and deserialization.
-   End-to-end Payment → Kafka → Notification workflow.
-   Notification persistence.
-   Logging notification provider execution.
-   PostgreSQL persistence verification.
-   Kafka UI topic verification.
-   Consumer group registration verification.

## Architecture

``` text
Payment Service
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
Notification Provider
```

## Outcome

The Kafka integration phase was completed successfully. The
implementation provides a working event-driven communication model
between the Payment and Notification modules, has been validated through
end-to-end testing, and is now considered **frozen** for the current
project scope.

------------------------------------------------------------------------







Maintained By

AeroSphere Development Project

Documentation Standard

Frozen (Version 1.0)
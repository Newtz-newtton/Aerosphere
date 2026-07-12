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


Maintained By

AeroSphere Development Project

Documentation Standard

Frozen (Version 1.0)
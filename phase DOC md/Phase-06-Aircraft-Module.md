# Phase 06 - Aircraft Module

## Project
**AeroSphere Airline Reservation and Operations Platform**

**Phase:** 06 – Aircraft Module
**Status:** Completed

---

## 1. Phase Overview
Phase 6 introduced the Aircraft master-data module following the frozen AeroSphere architecture and reusing all shared infrastructure.

## 2. Objectives
- Build Aircraft master-data module.
- Reuse common architecture.
- Secure write operations using role-based authorization.
- Validate CRUD operations.
- Verify PostgreSQL persistence.
- Add service layer unit testing.

## 3. Scope
- Aircraft Entity
- AircraftStatus
- Repository
- DTOs
- Mapper
- Service
- Controller
- CRUD APIs
- JWT
- @PreAuthorize
- Validation
- PostgreSQL
- Mockito Unit Test

## 4. Architecture
Client → JWT → @PreAuthorize → Controller → Service → Mapper → Repository → PostgreSQL

Shared:
- BaseEntity
- ApiResponse<T>
- GlobalExceptionHandler
- SecurityConfig
- JwtAuthenticationFilter
- JwtService
- OpenApiConfig

## 5. Package Structure
aircraft/
 controller/
 dto/{request,response}
 entity/
 mapper/
 repository/
 service/

## 6. Components
- AircraftStatus
- Aircraft
- AircraftRepository
- AircraftRequest
- AircraftResponse
- AircraftMapper
- AircraftService
- AircraftServiceImpl
- AircraftController
- AircraftServiceImplTest

## 7. Security
- JWT Protected APIs
- Swagger Bearer Authentication
- @PreAuthorize('ADMIN') on Create/Update/Delete

## 8. Validation
- Bean Validation
- Global Validation Exception Handling

## 9. Testing
Application Startup ✅
Hibernate ✅
CRUD ✅
Duplicate Validation ✅
Bean Validation ✅
PostgreSQL ✅
AircraftServiceImplTest ✅

## 10. Challenges
- Correct aircraft table naming.
- Added method-level authorization.
- Standardized Mockito service test template.

## 11. Lessons Learned
- Frozen architecture accelerates implementation.
- Mockito validates business logic independently.

## 12. Phase Outcome
Phase 6 completed successfully.

## 13. Next Phase
Phase 7 - Flight Module.

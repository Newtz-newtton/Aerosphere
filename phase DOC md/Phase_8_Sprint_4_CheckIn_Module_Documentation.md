# AeroSphere Airline Reservation & Operations Platform

# Phase 8 -- Sprint 4

# Check-in Module Documentation

## Sprint Overview

Implemented the Check-in module completing:

`User → Booking → Payment → Ticket → Check-in`

## Objectives

-   CheckIn entity
-   Ticket relationship
-   CRUD APIs
-   Business rules
-   Integration testing
-   Unit testing

## Package Structure

``` text
checkin/
├── controller
├── dto
├── entity
├── mapper
├── repository
└── service
```

## Entity

-   id
-   ticket
-   passengerName
-   flightNumber
-   checkInStatus
-   checkedInAt

## Relationship

Ticket (1) → CheckIn (1)

## Business Rules

-   Ticket must exist
-   One Check-in per Ticket
-   checkedInAt immutable
-   Snapshot passengerName
-   Snapshot flightNumber
-   CHECKED_IN default

## Repository

-   existsByTicketId()
-   findByTicketId()
-   findAllWithRelationships()
-   findByIdWithRelationships()

## Integration Testing (15/15 Passed)

1.  Create
2.  Invalid Ticket
3.  Duplicate
4.  Get All
5.  Get By ID
6.  Invalid ID
7.  Update
8.  Invalid Ticket Update
9.  Duplicate Update
10. Delete ADMIN
11. Delete STAFF
12. Invalid Delete
13. Validation
14. Response Mapping
15. Security

## Unit Testing (12/12 Passed)

-   createCheckIn (3)
-   getAll (1)
-   getById (2)
-   update (4)
-   delete (2)

## Outcome

Sprint 4 completed and frozen.

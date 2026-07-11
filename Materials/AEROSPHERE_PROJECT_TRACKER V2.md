AEROSPHERE MASTER TRACKER (v0.5.0)
1. PROJECT STATUS
   Project Name
   ────────────
   AeroSphere Airline Reservation and Operations Platform

Current Version
───────────────
v0.5.0

Current Phase
─────────────
Phase 5 Completed

Next Phase
──────────
Phase 6 — Aircraft

Overall Progress
────────────────
6 / 16 Major Phases
2. FROZEN ARCHITECTURE

These SHALL NOT CHANGE without your approval.

Architecture
────────────
✔ Modular Monolith

Language
────────
✔ Java 21

Framework
─────────
✔ Spring Boot

Database
────────
✔ PostgreSQL

ORM
───
✔ Hibernate JPA

Authentication
──────────────
✔ JWT

API
───
✔ REST

Documentation
─────────────
✔ Markdown
✔ DOCX

API Response
────────────
✔ ApiResponse<T>

Swagger
────────
✔ OpenAPI 3

Validation
──────────
✔ Jakarta Bean Validation

Security
────────
✔ Spring Security

Git Strategy
────────────
✔ One Logical Milestone = One Commit
3. PACKAGE ARCHITECTURE (FROZEN)
   com.aerosphere

admin
aircraft
airline
airport
auth
boarding
booking
checkin
common
config
exception
flight
notification
payment
security
ticket
user

Nothing will be added, removed, or renamed without approval.

4. STANDARD PACKAGE STRUCTURE (FROZEN)

Every business module follows:

module

controller

dto
request
response

entity

mapper

repository

service

No deviations.

5. COMMON INFRASTRUCTURE (TRACKED)

Completed:

BaseEntity

ApiResponse<T>

GlobalExceptionHandler

BusinessException

ResourceNotFoundException

UnauthorizedException

SecurityConfig

JwtAuthenticationFilter

JwtAuthenticationEntryPoint

JwtService

OpenApiConfig

PasswordEncoder

Swagger

Validation

Future modules will reuse these components without duplication.

6. CODING STANDARDS (FROZEN)

Every class includes:

Purpose
Responsibilities
Module

Every service:

Validation

↓

Repository

↓

Mapper

↓

DTO

Every controller:

Controller

↓

Service

↓

ApiResponse

Manual mapping only.

No MapStruct.

7. COMPLETED MODULES
   Phase 0

Planning

Completed

Requirements

Architecture

ADR

Database Design

Roadmap

API Design
Phase 1

Foundation

Completed

Java

Git

GitHub

PostgreSQL

Docker

Spring Boot

Hibernate

Initial Commit
Phase 2

Infrastructure

Completed

Common Module

Configuration

Swagger

Security Foundation

Exception Handling
Phase 3

Authentication

Completed

User

Role

Repository

Mapper

JWT

Login

Register

Password Encryption

Duplicate Validation

JWT Testing

Swagger

PostgreSQL
Phase 4

Airport

Completed

Airport Entity

AirportStatus

CRUD

Mapper

Repository

DTO

Service

Controller

Validation

JWT

Swagger

Database
Phase 5

Airline

Completed

Airline Entity

AirlineStatus

CRUD

Mapper

Repository

DTO

Service

Controller

Validation

JWT

Swagger

Database
8. FUTURE MODULES
   Phase 6

Aircraft

Planned

Aircraft

AircraftStatus

CRUD

JWT

Swagger

Testing
Phase 7

Flight

Planned

Flight

Route

Schedule

Relationships

Airline

Airport

Aircraft
Phase 8

Booking

Planned

Reservation

Booking

Passenger

Seat

Fare
Phase 9

Ticket

Planned

Ticket Generation

PNR

Barcode

Status
Phase 10

Passenger

Planned

Passenger Profile

Documents

Contact Details
Phase 11

Check-in

Planned

Check-in

Seat Allocation

Boarding Status
Phase 12

Frontend

Planned

React

Dashboard

Authentication

CRUD Pages
Phase 13

Testing

Planned

JUnit

Mockito

Integration Testing

API Testing
Phase 14

Deployment

Planned

Docker

Environment Variables

Production JWT Secret

Production Database

CI/CD
Phase 15

Documentation

Planned

Developer Handbook

Architecture Diagram

Deployment Guide

API Guide

Database Guide

ER Diagram

HLD

LLD

Final Review
9. ARCHITECTURE LINKER (TRACKED)

Current:

Authentication

↓

Airport

↓

Airline

Future:

Authentication
│
▼
Airline
│
▼
Aircraft
│
▼
Flight
┌─┼──────────────┐
│ │              │
▼ ▼              ▼
Airport           Booking
│
▼
Passenger
│
▼
Ticket
│
▼
Check-in
│
▼
Boarding
│
▼
Notification

Payment
▲
│
Booking ┘

This dependency graph is continuously maintained so future modules integrate cleanly.

10. GIT TRACKER

Completed:

Initial Commit

Project Foundation

Infrastructure

Authentication

Airport

Airline

Upcoming:

Aircraft

Flight

Booking

Ticket

Passenger

Check-in

Frontend

Testing

Deployment

Documentation
11. DOCUMENT TRACKER

Completed:

MASTER_PROJECT_STATUS

CHANGELOG

Phase 0

Phase 1

Phase 2

Phase 3

Phase 4

Phase 5

Pending:

Phase 6+

Developer Handbook

Deployment Guide

Architecture Book
12. TESTING TRACKER

Every module follows the same verification sequence:

Application Startup

Hibernate

Swagger

JWT

Create

Read All

Read By ID

Update

Delete

Duplicate Validation

Invalid ID

Bean Validation

Database Verification

This testing blueprint is frozen.

13. DEFERRED IMPROVEMENTS (TRACKED)

These are intentional enhancements we've decided to postpone rather than forget:

Environment variables for JWT secret and PostgreSQL credentials during deployment.
Replace Spring Security's default UserDetailsService with a custom database-backed implementation.
Centralize application messages as constants.
Final end-to-end architecture diagram (UI → Controllers → Services → Database).
Developer Handbook.
Deployment Guide.
Final ER diagram.
HLD and LLD consolidation.
CI/CD pipeline configuration.
Production Docker configuration.
14. PROJECT RULES (FROZEN)
    No architecture changes without your approval.
    No package structure changes without your approval.
    No database design changes without your approval.
    No business logic changes without your approval.
    One logical milestone equals one Git commit.
    Every phase ends with:
    Testing
    Documentation
    Git commit
    Git push
    Freeze
    All future modules follow the same implementation and testing blueprint.
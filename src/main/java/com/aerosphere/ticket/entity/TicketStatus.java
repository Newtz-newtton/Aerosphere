package com.aerosphere.ticket.entity;

/**
 * Purpose:
 * Represents the lifecycle states of a ticket.
 *
 * Responsibilities:
 * - Track ticket availability.
 * - Control ticket lifecycle.
 * - Support future check-in and boarding workflows.
 *
 * Module:
 * Ticket
 */
public enum TicketStatus {

    GENERATED,

    CANCELLED,

    USED

}
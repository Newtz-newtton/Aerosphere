package com.aerosphere.exception.custom;

/**
 * Purpose:
 * Represents exceptions thrown when a user is not authorized
 * to perform an operation.
 *
 * Module:
 * Exception
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
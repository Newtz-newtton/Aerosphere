package com.aerosphere.exception.custom;

/**
 * Purpose:
 * Represents exceptions thrown when a requested resource is not found.
 *
 * Module:
 * Exception
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
package com.aerosphere.exception.custom;

/**
 * Purpose:
 * Represents exceptions thrown when a business rule is violated.
 *
 * Module:
 * Exception
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
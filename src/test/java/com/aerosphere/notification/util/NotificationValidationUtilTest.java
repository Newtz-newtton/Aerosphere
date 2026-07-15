package com.aerosphere.notification.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Purpose:
 * Unit tests for NotificationValidationUtil.
 *
 * Responsibilities:
 * - Validate email addresses.
 * - Validate phone numbers.
 *
 * Module:
 * Notification
 */
class NotificationValidationUtilTest {

    @Test
    void isValidEmail_ShouldReturnTrue_ForValidEmail() {

        assertTrue(
                NotificationValidationUtil.isValidEmail(
                        "test@gmail.com"));
    }

    @Test
    void isValidEmail_ShouldReturnFalse_ForInvalidEmail() {

        assertFalse(
                NotificationValidationUtil.isValidEmail(
                        "invalid-email"));
    }

    @Test
    void isValidEmail_ShouldReturnFalse_ForNullEmail() {

        assertFalse(
                NotificationValidationUtil.isValidEmail(
                        null));
    }

    @Test
    void isValidPhoneNumber_ShouldReturnTrue_ForValidPhoneNumber() {

        assertTrue(
                NotificationValidationUtil.isValidPhoneNumber(
                        "+919876543210"));
    }

    @Test
    void isValidPhoneNumber_ShouldReturnFalse_ForInvalidPhoneNumber() {

        assertFalse(
                NotificationValidationUtil.isValidPhoneNumber(
                        "abc123"));
    }

    @Test
    void isValidPhoneNumber_ShouldReturnFalse_ForNullPhoneNumber() {

        assertFalse(
                NotificationValidationUtil.isValidPhoneNumber(
                        null));
    }

}
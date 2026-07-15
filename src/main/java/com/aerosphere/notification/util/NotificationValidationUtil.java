package com.aerosphere.notification.util;

/**
 * Purpose:
 * Provides reusable validation utilities
 * for notification recipients.
 *
 * Responsibilities:
 * - Validate email addresses.
 * - Validate phone numbers.
 *
 * Module:
 * Notification
 */
public final class NotificationValidationUtil {

    /**
     * Prevent instantiation.
     */
    private NotificationValidationUtil() {
    }

    /**
     * Validates an email address.
     *
     * @param email Email to validate.
     * @return true if valid.
     */
    public static boolean isValidEmail(String email) {

        return email != null
                && email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    /**
     * Validates an international phone number.
     * Supports E.164 format.
     *
     * Examples:
     * +919876543210
     * +14155552671
     *
     * @param phone Phone number.
     * @return true if valid.
     */
    public static boolean isValidPhoneNumber(
            String phone) {

        return phone != null
                && phone.matches(
                "^\\+?[1-9]\\d{9,14}$");
    }

}
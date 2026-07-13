package com.aerosphere.common.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Purpose:
 * Generates business reference numbers used across AeroSphere.
 *
 * Responsibilities:
 * - Generate booking references.
 * - Support future ticket, payment and boarding references.
 *
 * Module:
 * Common
 */
@Component
public class ReferenceGenerator {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd");

    public String generateBookingReference() {

        String date = LocalDate.now().format(DATE_FORMAT);

        String random =
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 6)
                        .toUpperCase();

        return "AS-BKG-" + date + "-" + random;
    }

    public String generatePaymentReference() {

        return "AS-PAY-"
                + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
                + "-"
                + UUID.randomUUID()
                .toString()
                .substring(0, 6)
                .toUpperCase();
    }

}
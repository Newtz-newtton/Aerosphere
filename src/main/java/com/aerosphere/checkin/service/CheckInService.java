package com.aerosphere.checkin.service;

import com.aerosphere.checkin.dto.request.CheckInRequest;
import com.aerosphere.checkin.dto.response.CheckInResponse;

import java.util.List;

/**
 * Purpose:
 * Defines business operations for passenger check-in management.
 *
 * Responsibilities:
 * - Create check-ins.
 * - Retrieve check-ins.
 * - Update check-ins.
 * - Delete check-ins.
 *
 * Module:
 * Check-in
 */
public interface CheckInService {

    CheckInResponse createCheckIn(CheckInRequest request);

    List<CheckInResponse> getAllCheckIns();

    CheckInResponse getCheckInById(Long id);

    CheckInResponse updateCheckIn(Long id,
                                  CheckInRequest request);

    void deleteCheckIn(Long id);

}
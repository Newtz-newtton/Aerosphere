package com.aerosphere.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Purpose:
 * Represents the request payload for user registration.
 *
 * Responsibilities:
 * - Captures user registration details.
 * - Validates required input fields.
 *
 * Module:
 * Authentication
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
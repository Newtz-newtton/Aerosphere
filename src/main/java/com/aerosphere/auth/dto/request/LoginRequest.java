package com.aerosphere.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Purpose:
 * Represents the request payload for user authentication.
 *
 * Responsibilities:
 * - Captures login credentials.
 *
 * Module:
 * Authentication
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
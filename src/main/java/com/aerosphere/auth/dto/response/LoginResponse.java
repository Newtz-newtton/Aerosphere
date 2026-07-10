package com.aerosphere.auth.dto.response;

import lombok.*;

/**
 * Purpose:
 * Represents the response returned after successful authentication.
 *
 * Responsibilities:
 * - Returns the generated JWT token.
 *
 * Module:
 * Authentication
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private String accessToken;

    private String tokenType;

}
package com.aerosphere.auth.dto.response;

import com.aerosphere.auth.entity.Role;
import lombok.*;

/**
 * Purpose:
 * Represents the response returned after successful user registration.
 *
 * Responsibilities:
 * - Returns newly registered user details.
 *
 * Module:
 * Authentication
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

}
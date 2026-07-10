package com.aerosphere.auth.mapper;

import com.aerosphere.auth.dto.request.RegisterRequest;
import com.aerosphere.auth.dto.response.RegisterResponse;
import com.aerosphere.auth.entity.Role;
import com.aerosphere.auth.entity.User;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Converts authentication DTOs into entities and vice versa.
 *
 * Responsibilities:
 * - Maps RegisterRequest to User.
 * - Maps User to RegisterResponse.
 *
 * Module:
 * Authentication
 */
@Component
public class UserMapper {

    public User toEntity(RegisterRequest request, String encodedPassword) {

        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(Role.CUSTOMER)
                .build();
    }

    public RegisterResponse toRegisterResponse(User user) {

        return RegisterResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

}
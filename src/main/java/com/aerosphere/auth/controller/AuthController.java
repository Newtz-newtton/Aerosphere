package com.aerosphere.auth.controller;

import com.aerosphere.auth.dto.request.LoginRequest;
import com.aerosphere.auth.dto.request.RegisterRequest;
import com.aerosphere.auth.dto.response.LoginResponse;
import com.aerosphere.auth.dto.response.RegisterResponse;
import com.aerosphere.auth.service.AuthService;
import com.aerosphere.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Purpose:
 * Exposes authentication REST endpoints.
 *
 * Responsibilities:
 * - Handles user registration.
 * - Handles user login.
 * - Delegates business logic to AuthService.
 *
 * Module:
 * Authentication
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        RegisterResponse response =
                authService.register(request);

        return ApiResponse.<RegisterResponse>builder()
                .success(true)
                .message("User registered successfully.")
                .data(response)
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response =
                authService.login(request);

        return ApiResponse.<LoginResponse>builder()
                .success(true)
                .message("Login successful.")
                .data(response)
                .build();
    }

}
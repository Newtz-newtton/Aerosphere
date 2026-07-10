package com.aerosphere.auth.service;

import com.aerosphere.auth.dto.request.LoginRequest;
import com.aerosphere.auth.dto.request.RegisterRequest;
import com.aerosphere.auth.dto.response.LoginResponse;
import com.aerosphere.auth.dto.response.RegisterResponse;

/**
 * Purpose:
 * Defines the authentication operations for the AeroSphere application.
 *
 * Responsibilities:
 * - Registers new users.
 * - Authenticates existing users.
 *
 * Module:
 * Authentication
 */
public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}
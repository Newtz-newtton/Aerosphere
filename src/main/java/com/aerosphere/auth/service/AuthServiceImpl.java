package com.aerosphere.auth.service;

import com.aerosphere.auth.dto.request.LoginRequest;
import com.aerosphere.auth.dto.request.RegisterRequest;
import com.aerosphere.auth.dto.response.LoginResponse;
import com.aerosphere.auth.dto.response.RegisterResponse;
import com.aerosphere.auth.entity.User;
import com.aerosphere.auth.mapper.UserMapper;
import com.aerosphere.auth.repository.UserRepository;
import com.aerosphere.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.aerosphere.security.JwtService;

/**
 * Purpose:
 * Implements the authentication business logic for AeroSphere.
 *
 * Responsibilities:
 * - Registers new users.
 * - Authenticates existing users.
 * - Coordinates repositories, mappers and security components.
 *
 * Module:
 * Authentication
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email is already registered.");
        }

        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        User user =
                userMapper.toEntity(request, encodedPassword);

        User savedUser =
                userRepository.save(user);

        return userMapper.toRegisterResponse(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new BusinessException("Invalid email or password."));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new BusinessException(
                    "Invalid email or password.");
        }

        String token =
                jwtService.generateToken(user.getEmail());

        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .build();
    }

}
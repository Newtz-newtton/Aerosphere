package com.aerosphere.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Purpose:
 * Configures password encoding for the AeroSphere application.
 *
 * Responsibilities:
 * - Provides a PasswordEncoder bean.
 * - Uses BCrypt for secure password hashing.
 *
 * Module:
 * Configuration
 */
@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
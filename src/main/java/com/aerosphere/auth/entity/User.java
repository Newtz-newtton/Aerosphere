package com.aerosphere.auth.entity;

import com.aerosphere.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * Purpose:
 * Represents an authenticated user in the AeroSphere system.
 *
 * Responsibilities:
 * - Stores user authentication information.
 * - Stores user authorization role.
 * - Serves as the primary identity entity.
 *
 * Module:
 * Authentication
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
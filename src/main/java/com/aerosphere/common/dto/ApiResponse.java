package com.aerosphere.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Class Name : ApiResponse
 *
 * Purpose :
 *     Standard response wrapper returned by every REST API in AeroSphere.
 *
 * Responsibilities :
 *     - Maintain consistent API responses.
 *     - Provide a standard success/failure structure.
 *     - Improve frontend integration.
 *
 * Module :
 *     Common
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {


    private boolean success;
    private String message;
    private T data;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}

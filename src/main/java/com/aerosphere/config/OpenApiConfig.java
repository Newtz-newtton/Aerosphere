package com.aerosphere.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Purpose:
 * Configures OpenAPI (Swagger) documentation for the AeroSphere REST APIs.
 *
 * Responsibilities:
 * - Provides API metadata.
 * - Enables Swagger UI.
 * - Improves API discoverability and testing.
 *
 * Module:
 * Configuration
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI aeroSphereOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("AeroSphere API")
                        .description("Airline Reservation and Operations Platform")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Newtton Habakuk")
                                .email("newttonhabakuk.s@gmail.com"))
                        .license(new License()
                                .name("MIT License")))
                .externalDocs(new ExternalDocumentation()
                        .description("AeroSphere Documentation"));
    }
}
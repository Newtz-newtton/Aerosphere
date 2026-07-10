package com.aerosphere.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Purpose:
 * Configures the OpenAPI (Swagger) documentation for AeroSphere.
 *
 * Responsibilities:
 * - Configure API metadata.
 * - Configure JWT authentication for Swagger.
 *
 * Module:
 * Configuration
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI aeroSphereOpenAPI() {

        final String securitySchemeName = "Bearer Authentication";

        return new OpenAPI()

                .info(new Info()

                        .title("AeroSphere API")

                        .version("1.0.0")

                        .description("Airline Reservation and Operations Platform")

                        .contact(new Contact()
                                .name("Newtton Habakuk")
                                .email("newttonhabakuk.s@gmail.com")))

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName))

                .schemaRequirement(
                        securitySchemeName,

                        new SecurityScheme()

                                .name(securitySchemeName)

                                .type(SecurityScheme.Type.HTTP)

                                .scheme("bearer")

                                .bearerFormat("JWT"));
    }
}
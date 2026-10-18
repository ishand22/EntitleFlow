package com.entitleflow.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI entitleFlowOpenAPI() {
        return new OpenAPI().info(new Info().title("EntitleFlow API")
                .version("1.0.0").description("Customer, hardware asset, order, and software entitlement management."));
    }
}

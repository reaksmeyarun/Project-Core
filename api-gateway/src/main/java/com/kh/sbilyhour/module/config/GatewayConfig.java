package com.kh.sbilyhour.module.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_service", r -> r.path("/user/v1/{operation}/**")
                        .filters(f -> f.rewritePath("/user/v1/(?<operation>.*)", "/user/v1/${operation}"))  // Keep full path
                        .uri("http://localhost:8081"))  // Ensure URI is correct
                .build();
    }
}
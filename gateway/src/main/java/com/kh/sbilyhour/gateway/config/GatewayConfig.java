package com.kh.sbilyhour.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${gateway.user-service-uri}")
    private String userServiceUri;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_service", r -> r.path("/user/v1/{operation}/**")
                        .filters(f -> f.rewritePath("/user/v1/(?<operation>.*)", "/user/v1/${operation}"))  // Keep full path
                        .uri(userServiceUri))  // Ensure URI is correct
                .build();
    }
}
package com.kh.sbilyhour.module.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GatewayConfig {

    @Value("${gateway.uri}")
    private String gatewayUri; // The base URI for the backend service

    @Value("${spring.cloud.gateway.validate-host.allowed-hosts:localhost}")
    private List<String> allowedHosts; // List of allowed hosts from the configuration

    private final ValidateHostGatewayFilterFactory validateHostGatewayFilterFactory;

    @Autowired
    public GatewayConfig(ValidateHostGatewayFilterFactory validateHostGatewayFilterFactory) {
        this.validateHostGatewayFilterFactory = validateHostGatewayFilterFactory;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user", r -> r.path("/user/v1/{operation}/**")
                        .filters(f -> f
                                .rewritePath("/user/v1/(?<operation>.*)", "/user/v1/${operation}")  // Keep full path
                                .filter(validateHostGatewayFilterFactory.apply(c -> c.setAllowedHosts(allowedHosts))) // Configure allowed hosts
                        )
                        .uri(gatewayUri))  // Use the URI from configuration
                .build();
    }
}

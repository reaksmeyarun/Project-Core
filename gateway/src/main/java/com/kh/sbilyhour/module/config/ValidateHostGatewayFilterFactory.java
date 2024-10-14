package com.kh.sbilyhour.module.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class ValidateHostGatewayFilterFactory extends AbstractGatewayFilterFactory<ValidateHostGatewayFilterFactory.Config> {

    @Value("${spring.cloud.gateway.validate-host.allowed-hosts:localhost}")
    private List<String> allowedHosts;  // Fetch allowed hosts from the configuration

    public ValidateHostGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String host = exchange.getRequest().getURI().getHost();
            if (!allowedHosts.contains(host)) {
                return Mono.error(new IllegalArgumentException("Host not allowed: " + host));
            }
            return chain.filter(exchange);
        };
    }

    @Override
    public String name() {
        return "ValidateHost";
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("allowedHosts");
    }

    @Data
    public static class Config {
        private List<String> allowedHosts;
    }
}

package com.ss.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancedRoutesConfig {

    public static final String DEMO_SERVICE = "lb://demo-service";
    public static final String DEMOAUTH_SERVICE = "lb://demoauth-service";
    public static final String DEMOCUSTOMER_SERVICE = "lb://democustomer-service";

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/login")
                        .uri(DEMOAUTH_SERVICE))
                .route(r -> r.path("/customer" + "/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri(DEMOCUSTOMER_SERVICE))
                .route(r -> r.path("/demo" + "/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri(DEMO_SERVICE))
                .build();
    }
}

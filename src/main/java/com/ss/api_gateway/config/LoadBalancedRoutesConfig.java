package com.ss.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancedRoutesConfig {

    public static final String AUTH_SERVICE = "lb://scrumptious-auth-service";
    public static final String CUSTOMER_SERVICE = "lb://scrumptious-customer-service";
    public static final String RESTAURANT_SERVICE = "lb://scrumptious-restaurant-service";
    public static final String DRIVER_SERVICE = "lb://scrumptious-driver-service";
    public static final String ORDER_SERVICE = "lb://scrumptious-order-service";
    public static final String NOTIFICATION_SERVICE = "lb://scrumptious-notification-service";
    public static final String DELIVERY_SERVICE = "lb://scrumptious-delivery-service";

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/auth" + "/**").filters(f -> f.stripPrefix(1)).uri(AUTH_SERVICE))
                .route(r -> r.path("/customer" + "/**").filters(f -> f.stripPrefix(1)).uri(CUSTOMER_SERVICE))
                .route(r -> r.path("/restaurant" + "/**").filters(f -> f.stripPrefix(1)).uri(RESTAURANT_SERVICE))
                .route(r -> r.path("/driver" + "/**").filters(f -> f.stripPrefix(1)).uri(DRIVER_SERVICE))
                .route(r -> r.path("/order" + "/**").filters(f -> f.stripPrefix(1)).uri(ORDER_SERVICE))
                .route(r -> r.path("/delivery" + "/**").filters(f -> f.stripPrefix(1)).uri(DELIVERY_SERVICE))
                .route(r -> r.path("/notification" + "/**").filters(f -> f.stripPrefix(1)).uri(NOTIFICATION_SERVICE))
                .build();
    }
}

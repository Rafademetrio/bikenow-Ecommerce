package com.rafademetrio.bikenow_gateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("auth-service", r-> r.path("/auth/**").uri("lb:/bikenew-auth")) //routes to  authentication.
                .route("product-service", r -> r.path("/api/products/**")
                        //TODO.filters(f -> f.filter(jwtAuthenticationFilter())) // Aplica filtro JWT
                        .uri("lb://bikenow-product"))
                .route("order-service", r -> r.path("/api/orders/**")
                        //TODO.filters(f -> f.filter(jwtAuthenticationFilter()))
                        .uri("lb://bikenow-order"))

                .route("user-service", r -> r.path("/api/users/**")
                        //TODO.filters(f -> f.filter(jwtAuthenticationFilter()))
                        .uri("lb://bikenow-user"))
                .build();
    }
}

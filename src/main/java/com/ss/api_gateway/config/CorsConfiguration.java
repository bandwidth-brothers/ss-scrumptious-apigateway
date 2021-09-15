package com.ss.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfiguration {

    public static final String ALLOWED_ORIGIN_KEY = "Access-Control-Allow-Origin";
    public static final String ALLOWED_HEADERS_KEY = "Access-Control-Allow-Headers";
    public static final List<String> HEADERS_LIST = Arrays.asList(
            "x-requested-with",
            "authorization",
            "Content-Type",
            "Authorization",
            "credential",
            "X-XSRF-TOKEN"
    );
    public static final String ALLOWED_HEADERS_VALUE = String.join(", ", HEADERS_LIST);
    public static final String MAX_AGE_KEY = "Access-Control-Max-Age";
    public static final String MAX_AGE_VALUE = "3600";
    public static final String ALLOWED_METHODS_KEY = "Access-Control-Allow-Methods";
    public static final List<String> ALLOWED_METHODS = Arrays.asList(
            "GET", "PUT", "POST", "DELETE", "OPTIONS"
    );
    public static final String ALLOWED_METHODS_VALUE = String.join(", ", ALLOWED_METHODS);
    private static final String ALLOWED_ORIGIN = "*";

    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest  request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders headers = response.getHeaders();

                headers.add(ALLOWED_ORIGIN_KEY, ALLOWED_ORIGIN);
                headers.add(ALLOWED_METHODS_KEY, ALLOWED_METHODS_VALUE);
                headers.add(MAX_AGE_KEY, MAX_AGE_VALUE);
                headers.add(ALLOWED_HEADERS_KEY, ALLOWED_HEADERS_VALUE);

                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }
}

package com.ss.api_gateway.config;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class JwtTokenFilter implements GlobalFilter, Ordered {


    private final SecurityConstants securityConstants;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getPath().toString();
        ServerHttpResponse response = exchange.getResponse();

        final List<String> apiEndpoints = Arrays.asList("/auth/login"
                ,"/auth/accounts/register"
                ,"/swagger-ui"
                ,"/swagger-ui.html"
                ,"/v2/api-docs"
                ,"/swagger-resources"
                ,"/restaurant/owner/register"
                ,"/restaurant/admin/register"
                ,"/customer/register"
                ,"/driver/register"
                ,"/auth/health");

        //System.out.println("URL: " + requestUrl);
        boolean matches = apiEndpoints.stream().anyMatch(s -> {
            if (requestUrl.contains(s)) {
                return true;
            } else {
                return false;
            }
        });

        if (matches){
            return chain.filter(exchange);
        }

        System.out.println("securityConstants: " + securityConstants.getHEADER_STRING());
        //get header
        String header = exchange.getRequest().getHeaders().getFirst(securityConstants.getHEADER_STRING());

        //check token
        if(header==null || header=="" || !header.startsWith(securityConstants.getTOKEN_PREFIX())){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        try{
            String token = header.replace(securityConstants.getTOKEN_PREFIX(), "");
            Algorithm algorithm = Algorithm.HMAC512(securityConstants.getSECRET());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
//            String sub = jwt.getSubject();
            Date exp = jwt.getExpiresAt();

            if(!isValidDate(exp)){
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        }
        catch(Exception ex){
            log.error("token error: " + ex);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public static boolean isValidDate(Date exp) throws ParseException {
        return new Date().before(exp);
    }
}

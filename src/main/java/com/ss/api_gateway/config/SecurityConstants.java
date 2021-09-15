package com.ss.api_gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Date;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
@PropertySource("classpath:securityconstants.properties")
public class SecurityConstants {

    private String SECRET;
    private String EXPIRATION_DURATION; // 10 days
    private String TOKEN_PREFIX;
    private String HEADER_STRING;

    public Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + Long.valueOf(this.EXPIRATION_DURATION.replaceAll("_", "")));
    }

}

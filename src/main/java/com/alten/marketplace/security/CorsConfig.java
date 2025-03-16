package com.alten.marketplace.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        List<String> allowedMethods = new ArrayList<>();
        List<String> origins = new ArrayList<>();
        List<String> allowedHeaders = new ArrayList<>();
        List<String> exposedHeaders = new ArrayList<>();
        allowedMethods.add("POST");
        allowedMethods.add("GET");
        allowedMethods.add("DELETE");
        allowedMethods.add("PUT");
        allowedMethods.add("PATCH");
        origins.add("*");
        allowedHeaders.add("Authorization");
        allowedHeaders.add("Accept");
        allowedHeaders.add("X-Requested-With");
        allowedHeaders.add("Content-Type");
        allowedHeaders.add("Access-Control-Request-Method");
        allowedHeaders.add("Access-Control-Request-Headers");

        exposedHeaders.add("Access-Control-Allow-Origin");
        exposedHeaders.add("Access-Control-Allow-Credentials");
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);

        config.setAllowedOrigins(origins);
        config.setAllowedMethods(allowedMethods);

        config.setAllowedHeaders(allowedHeaders);

        config.setExposedHeaders(exposedHeaders);

        config.setMaxAge(6600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
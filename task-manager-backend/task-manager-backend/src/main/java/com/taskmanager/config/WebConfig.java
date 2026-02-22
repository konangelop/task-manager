package com.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Web configuration for CORS (Cross-Origin Resource Sharing).
 * 
 * CORS is necessary because your React frontend (localhost:5173 or localhost:3000)
 * runs on a different port than your Spring Boot backend (localhost:8080).
 * 
 * Without CORS configuration, browsers block requests from your React app
 * to the backend API due to the Same-Origin Policy.
 * 
 * This is important to understand for frontend development:
 * - In development, you'll often have CORS issues if the backend isn't configured
 * - In production, you'd typically serve both from the same domain or use a proxy
 * - Vite (React dev server) can also proxy requests to avoid CORS in development
 */
@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        // Allow requests from React development servers
        config.setAllowedOrigins(Arrays.asList(
            "http://localhost:5173",  // Vite default port
            "http://localhost:3000",  // CRA default port
            "http://127.0.0.1:5173",
            "http://127.0.0.1:3000"
        ));
        
        // Allow common HTTP methods
        config.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
        ));
        
        // Allow common headers
        config.setAllowedHeaders(Arrays.asList(
            "Authorization",
            "Content-Type",
            "X-Requested-With",
            "Accept",
            "Origin"
        ));
        
        // Allow credentials (cookies, authorization headers)
        config.setAllowCredentials(true);
        
        // How long the preflight response can be cached
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);
        
        return new CorsFilter(source);
    }
}

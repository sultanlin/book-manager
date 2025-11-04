package com.sultanlinjawi.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration conf = new CorsConfiguration();
        // conf.setAllowCredentials(true);
        conf.setAllowedOrigins(Arrays.asList("*"));
        conf.setAllowedMethods(Arrays.asList("*"));
        conf.setAllowedHeaders(Arrays.asList("*"));
        // conf.addAllowedHeader(HttpHeaders.CONTENT_TYPE);
        // conf.addAllowedHeader(HttpHeaders.AUTHORIZATION);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", conf);
        return source;
    }
}

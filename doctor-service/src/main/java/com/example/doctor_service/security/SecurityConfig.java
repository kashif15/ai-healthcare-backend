package com.example.doctor_service.security;

import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers(HttpMethod.POST, "/api/doctors/register", "/api/doctors/login").permitAll()

                        // Doctor-only POST (availability)
                        .requestMatchers(HttpMethod.POST, "/api/doctors/*/availability").hasRole("DOCTOR")

                        // Authenticated users (doctor or user) can access GET endpoints
                        .requestMatchers(HttpMethod.GET, "/api/doctors/**").authenticated()

                        // PATCH booking endpoint accessible by authenticated users too
                        .requestMatchers(HttpMethod.PATCH, "/api/doctors/book/**").authenticated()

                        // All others must be authenticated
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

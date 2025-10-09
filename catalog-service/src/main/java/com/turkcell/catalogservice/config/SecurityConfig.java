package com.turkcell.catalogservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(
                       authorizeRequests ->
                               authorizeRequests
                                       .requestMatchers("/actuator/**").permitAll()
                                       .requestMatchers("/swagger-ui/**").permitAll()
                                       .requestMatchers("/v3/api-docs/**").permitAll()
                                       .anyRequest().authenticated()
               )
               .oauth2ResourceServer(o -> {
                   // JWT Converter
                   o.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter()));
               })
               .build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        return converter;
    }
}

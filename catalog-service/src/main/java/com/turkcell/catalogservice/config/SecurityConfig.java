package com.turkcell.catalogservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
                                       .requestMatchers("/h2-console/**").permitAll()
                                       .requestMatchers(HttpMethod.GET,"/api/v2/products/**").hasAnyAuthority("READ_PRODUCT")
                                       .anyRequest().permitAll()
               )
               .oauth2ResourceServer(o -> {
                   // JWT Converter
                   o.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter()));
               })
               .build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        // Keycloak jwtsini istediğim biçimde convert etmemi sağlayan.
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(this::extractAuthorities);
        return converter;
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt)
    {
        List<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, Object> realmAccess = jwt.getClaim("realm_access");

        if(realmAccess != null)
        {
            List<String> roles = (List<String>)realmAccess.get("roles");
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        }

        return authorities;
    }
}

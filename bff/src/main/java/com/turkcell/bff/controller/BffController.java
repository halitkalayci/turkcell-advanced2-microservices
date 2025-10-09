package com.turkcell.bff.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class BffController {
    private WebClient webClient;

    public BffController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/me")
    public Mono<Map<String,Object>> me(@AuthenticationPrincipal OidcUser user) {
        return Mono.just(
                Map.of(
                        "sub", user.getSubject(),
                        "username", user.getPreferredUsername(),
                        "email", user.getEmail(),
                        "roles", user.getAuthorities()
                )
        );
    }

    @GetMapping("/example/{id}")
    public Mono<String> example(@PathVariable("id") String id) {
        return webClient.get()
                .uri("http://localhost:8080/api/v2/products/"+id)
                .retrieve()
                .bodyToMono(String.class);
    }
}

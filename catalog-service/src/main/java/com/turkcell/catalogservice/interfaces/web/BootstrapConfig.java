package com.turkcell.catalogservice.interfaces.web;

import com.turkcell.catalogservice.application.product.service.ProductApplicationService;
import com.turkcell.catalogservice.domain.repository.ProductRepository;
import com.turkcell.catalogservice.infrastructure.repo.InMemoryProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BootstrapConfig
{
    @Bean
    public ProductApplicationService productApplicationService(ProductRepository productRepository) {
        return new ProductApplicationService(productRepository);
    }
}

package com.turkcell.catalogservice.infrastructure.repo;

import com.turkcell.catalogservice.domain.Product;
import com.turkcell.catalogservice.domain.ProductId;
import com.turkcell.catalogservice.domain.repository.ProductRepository;

import java.util.Optional;

public class JpaProductRepositoryAdapter implements ProductRepository {
    private final SpringDataProductRepository repository;

    public JpaProductRepositoryAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return Optional.empty();
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }
}

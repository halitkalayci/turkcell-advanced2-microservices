package com.turkcell.catalogservice.infrastructure.repo;

import com.turkcell.catalogservice.domain.Product;
import com.turkcell.catalogservice.domain.ProductId;
import com.turkcell.catalogservice.domain.repository.ProductRepository;
import com.turkcell.catalogservice.infrastructure.entity.ProductEntity;
import com.turkcell.catalogservice.infrastructure.mapper.ProductPersistenceMapper;

import java.util.Optional;

public class JpaProductRepositoryAdapter implements ProductRepository {
    private final SpringDataProductRepository repository;

    public JpaProductRepositoryAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = ProductPersistenceMapper.toEntity(product);
        productEntity = repository.save(productEntity);
        return ProductPersistenceMapper.toDomain(productEntity);
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        Optional<ProductEntity> entity = repository.findById(id.value());
        return entity.map(ProductPersistenceMapper::toDomain);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByNameIgnoreCase(name);
    }
}

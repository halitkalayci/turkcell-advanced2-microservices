package com.turkcell.catalogservice.domain.repository;

import com.turkcell.catalogservice.domain.Product;
import com.turkcell.catalogservice.domain.ProductId;

import java.util.Optional;

public interface ProductRepository
{
    Product save(Product product);
    Optional<Product> findById(ProductId id);
    boolean existsByName(String name);
}

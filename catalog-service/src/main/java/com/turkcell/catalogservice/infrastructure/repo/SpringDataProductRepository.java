package com.turkcell.catalogservice.infrastructure.repo;

import com.turkcell.catalogservice.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataProductRepository extends JpaRepository<ProductEntity, UUID> {
    boolean existsByNameIgnoreCase(String name);
}

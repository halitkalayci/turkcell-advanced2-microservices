package com.turkcell.catalogservice.application.product.service;

import com.turkcell.catalogservice.application.product.dto.CreateProductRequest;
import com.turkcell.catalogservice.application.product.dto.CreatedProductResponse;
import com.turkcell.catalogservice.application.product.mapper.ProductMapper;
import com.turkcell.catalogservice.domain.Product;
import com.turkcell.catalogservice.domain.repository.ProductRepository;

public class ProductApplicationService
{
    private final ProductRepository productRepository;

    public ProductApplicationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public CreatedProductResponse create(CreateProductRequest createProductRequest) {
        if(productRepository.existsByName(createProductRequest.name()))
            throw new IllegalArgumentException("Product name already exists");

        Product product = ProductMapper.toEntity(createProductRequest);
        product = productRepository.save(product);
        return ProductMapper.toResponse(product);
    }
}

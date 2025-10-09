package com.turkcell.catalogservice.application.product.service;

import com.turkcell.catalogservice.application.product.dto.CreateProductRequest;
import com.turkcell.catalogservice.application.product.dto.CreatedProductResponse;
import com.turkcell.catalogservice.application.product.dto.GetByIdProductResponse;
import com.turkcell.catalogservice.application.product.mapper.ProductMapper;
import com.turkcell.catalogservice.domain.Product;
import com.turkcell.catalogservice.domain.ProductId;
import com.turkcell.catalogservice.domain.repository.ProductRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;
import java.util.UUID;

public class ProductApplicationService
{
    private final ProductRepository productRepository;

    public ProductApplicationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Ya kendi kullanıcı adımı aramalıyım ya da ADMIN olmalıyım.
    //@PreAuthorize("#userName == authentication.principal.username || hasAnyAuthority('ADMIN')")
    public Object getUser(String userName) {
        return null;
    }
   //@PostAuthorize("returnObject.ownerId == #id")
    public Object getDocument(UUID id)
    {
        return null;
    }

   //@PreAuthorize("isAuthenticated()")
    public CreatedProductResponse create(CreateProductRequest createProductRequest) {
        if(productRepository.existsByName(createProductRequest.name()))
            throw new IllegalArgumentException("Product name already exists");

        Product product = ProductMapper.toEntity(createProductRequest);
        product = productRepository.save(product);
        return ProductMapper.toResponse(product);
    }
    //@PreAuthorize("hasAnyAuthority('ADMIN','READ_PRODUCT')")
    public GetByIdProductResponse getByID(UUID id)
    {
        Optional<Product> product = productRepository.findById(new ProductId(id));
        if (product.isEmpty())
            throw new IllegalArgumentException("Product not found");

        return ProductMapper.toGetByIdResponse(product.get());
    }
}

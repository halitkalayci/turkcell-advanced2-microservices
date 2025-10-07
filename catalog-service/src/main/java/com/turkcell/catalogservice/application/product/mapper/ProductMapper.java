package com.turkcell.catalogservice.application.product.mapper;

import com.turkcell.catalogservice.application.product.dto.CreateProductRequest;
import com.turkcell.catalogservice.application.product.dto.CreatedProductResponse;
import com.turkcell.catalogservice.application.product.dto.GetByIdProductResponse;
import com.turkcell.catalogservice.domain.Money;
import com.turkcell.catalogservice.domain.Product;
import org.springframework.stereotype.Component;

public final class ProductMapper {
    private ProductMapper() {}

    public static CreatedProductResponse toResponse(Product product) {
        return new CreatedProductResponse(
                product.id().value(),
                product.name(),
                product.description(),
                product.money().currency(),
                product.money().amount(),
                product.stock()
        );
    }

    public static Product toEntity(CreateProductRequest request) {
        return Product.create(
                request.name(),
                request.description(),
                new Money(request.amount(), request.currency()),
                request.stock()
        );
    }

    public static GetByIdProductResponse toGetByIdResponse(Product product) {
        return new GetByIdProductResponse(product.id().value(), product.name(), product.stock());
    }
}

package com.turkcell.catalogservice.infrastructure.mapper;

import com.turkcell.catalogservice.domain.Money;
import com.turkcell.catalogservice.domain.Product;
import com.turkcell.catalogservice.domain.ProductId;
import com.turkcell.catalogservice.infrastructure.entity.ProductEntity;

public final class ProductPersistenceMapper {
    public static ProductEntity toEntity(Product product){
        return new ProductEntity(
                product.id().value(),
                product.name(),
                product.money().amount(),
                product.money().currency(),
                product.description(),
                product.createdAt(),
                product.updatedAt(),
                product.stock()
        );
    }

    // Veritabanından okudugunu domaine çevir.
    public static Product toDomain(ProductEntity productEntity){
        return Product.rehydrate(
                new ProductId(productEntity.id()),
                productEntity.name(),
                productEntity.description(),
                new Money(productEntity.price(), productEntity.currency()),
                productEntity.stock(),
                productEntity.createdAt(),
                productEntity.updatedAt()
        );
    }
}

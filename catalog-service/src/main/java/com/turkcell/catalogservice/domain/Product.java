package com.turkcell.catalogservice.domain;

import java.time.OffsetDateTime;

// Aggregate
public class Product {
    private final ProductId id;
    private String name;
    private Money money;
    private String description;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private int stock;
    // Kontrollü Üretim
    private Product(ProductId id,
                   String name,
                   String description,
                   Money money,
                   int stock,
                   OffsetDateTime createdAt,
                   OffsetDateTime updatedAt
                   ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.stock = stock;
        this.money = money;
    }

    // Static Factory Method -
    // TODO: Rehydrate Method
    public static Product create(String name,
                                 String description,
                                 Money money,
                                 int stock) {
        return new Product(
                ProductId.newId(),
                name,
                description,
                money,
                stock,
                OffsetDateTime.now(),
                null
        );
    }

    public void dispatch(int quantity)
    {
        if(quantity <= 0)
            throw new IllegalArgumentException("Quantity must be greater than zero");

        if(quantity > stock)
            throw new IllegalArgumentException("Quantity must be less than stock");

        this.stock -= quantity;
    }
}
// Action-Based -> dispatch(10), restock(20); setName X rename()
// Setter -> setStock(10); Değer override eder.
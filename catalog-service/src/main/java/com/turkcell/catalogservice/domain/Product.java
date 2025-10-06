package com.turkcell.catalogservice.domain;

import java.time.OffsetDateTime;
import java.util.Objects;

// Aggregate
public class Product {
    private final ProductId id;
    private String name;
    private Money money;
    private String description;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private int stock;


    private Product(ProductId id,
                   String name,
                   String description,
                   Money money,
                   int stock,
                   OffsetDateTime createdAt,
                   OffsetDateTime updatedAt
                   ) {
        this.id = Objects.requireNonNull(id, "Product id must not be null");
        rename(name);
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.stock = stock;
        this.money = money;
    }

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

    // ------ Behavior (Setters) ----------
    public void rename(String newName) {
        if(newName.isBlank())
            throw new IllegalArgumentException("Name cannot be blank");
        if(newName.length() > 255)
            throw new IllegalArgumentException("Name cannot be longer than 255 characters");
        this.name = newName;
    }

    public void dispatch(int quantity) {
        if(quantity <= 0)
            throw new IllegalArgumentException("Quantity must be greater than zero");

        if(quantity > stock)
            throw new IllegalArgumentException("Quantity must be less than stock");

        this.stock -= quantity;
    }
    // ------ Behavior (Setters) ----------

    // ------ Getters ----------
    public ProductId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Money money() {
        return money;
    }

    public String description() {
        return description;
    }

    public OffsetDateTime createdAt() {
        return createdAt;
    }

    public OffsetDateTime updatedAt() {
        return updatedAt;
    }

    public int stock() {
        return stock;
    }
    // ------ Getters ----------
}
// Action-Based -> dispatch(10), restock(20); setName X rename()
// Setter -> setStock(10); Değer override eder.

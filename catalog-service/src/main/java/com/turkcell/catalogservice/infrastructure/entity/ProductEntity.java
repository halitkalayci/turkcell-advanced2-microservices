package com.turkcell.catalogservice.infrastructure.entity;

import com.turkcell.catalogservice.domain.Money;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="products")
public class ProductEntity {
    @Id
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID id;
    private String name;
    private BigDecimal price;
    private String currency;
    private String description;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private int stock;

    public ProductEntity() {
    }

    public ProductEntity(UUID id, String name, BigDecimal price, String currency, String description, OffsetDateTime createdAt, OffsetDateTime updatedAt, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.stock = stock;
    }

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal price() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String currency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime createdAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime updatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int stock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

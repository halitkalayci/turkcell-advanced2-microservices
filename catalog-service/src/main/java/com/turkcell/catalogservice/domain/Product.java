package com.turkcell.catalogservice.domain;

import java.time.OffsetDateTime;

// Aggregate
public class Product {
    private final ProductId id;
    private String name;
    private String description;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private int stock;
}

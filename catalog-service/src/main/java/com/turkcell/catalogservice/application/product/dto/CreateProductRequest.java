package com.turkcell.catalogservice.application.product.dto;

import java.math.BigDecimal;

public record CreateProductRequest(
        String name,
        String description,
        String currency,
        BigDecimal amount,
        Integer stock)
{
}

package com.turkcell.catalogservice.application.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatedProductResponse(
        UUID id,
        String name,
        String description,
        String currency,
        BigDecimal amount,
        Integer stock
) {
}

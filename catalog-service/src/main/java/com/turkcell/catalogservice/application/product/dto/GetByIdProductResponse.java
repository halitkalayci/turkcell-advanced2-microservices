package com.turkcell.catalogservice.application.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record GetByIdProductResponse(UUID id, String name, int stock, BigDecimal price) { }

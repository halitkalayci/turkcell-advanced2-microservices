package com.turkcell.order_service.contract;

import java.math.BigDecimal;
import java.util.UUID;

public record CatalogGetByIdProductContract(UUID id, int stock, String name, BigDecimal price) { }

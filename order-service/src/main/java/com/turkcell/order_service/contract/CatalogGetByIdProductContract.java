package com.turkcell.order_service.contract;

import java.util.UUID;

public record CatalogGetByIdProductContract(UUID id, int stock) { }

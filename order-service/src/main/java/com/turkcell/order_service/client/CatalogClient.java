package com.turkcell.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// Diğer servisteki controller konfigürasyonu
@FeignClient(name="catalog-service", path = "/api/v2/products")
public interface CatalogClient
{
    @GetMapping()
    String get();
}

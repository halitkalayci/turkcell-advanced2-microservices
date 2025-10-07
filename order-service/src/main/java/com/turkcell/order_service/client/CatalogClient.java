package com.turkcell.order_service.client;

import com.turkcell.order_service.contract.CatalogGetByIdProductContract;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

// Diğer servisteki controller konfigürasyonu
@FeignClient(name="catalog-service", path = "/api/v2/products")
public interface CatalogClient
{
    @GetMapping("{id}")
    CatalogGetByIdProductContract get(@PathVariable("id") UUID id);
}

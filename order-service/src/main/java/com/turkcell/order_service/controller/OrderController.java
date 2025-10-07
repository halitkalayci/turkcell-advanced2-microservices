package com.turkcell.order_service.controller;

import com.turkcell.order_service.client.CatalogClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final CatalogClient catalogClient;

    public OrderController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping
    public String get() {
        var response = catalogClient.get(UUID.fromString("8ea2cfe2-d805-40dc-9435-51db774292b1"));
        System.out.println("Response: " + response);
        System.out.println("Order service working..");
        return "Order-Service working";
    }
}

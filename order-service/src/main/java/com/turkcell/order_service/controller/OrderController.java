package com.turkcell.order_service.controller;

import com.turkcell.order_service.client.CatalogClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final CatalogClient catalogClient;

    public OrderController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping
    public String get() {
        String response = catalogClient.get();
        System.out.println("Response: " + response);
        System.out.println("Order service working..");
        return "Order-Service working";
    }
}

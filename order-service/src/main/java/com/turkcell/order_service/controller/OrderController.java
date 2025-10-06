package com.turkcell.order_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    @GetMapping
    public String get() {
        System.out.println("Order service working..");
        return "Order-Service working";
    }
}

package com.turkcell.catalogservice.interfaces.web.controller;

import com.turkcell.catalogservice.application.product.dto.CreateProductRequest;
import com.turkcell.catalogservice.application.product.dto.CreatedProductResponse;
import com.turkcell.catalogservice.application.product.service.ProductApplicationService;
import com.turkcell.catalogservice.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v2/products")
@RestController
public class ProductsController {
    private final ProductApplicationService productApplicationService;

    public ProductsController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreatedProductResponse> addProduct(@RequestBody CreateProductRequest createProductRequest) {
        return ResponseEntity.ok(this.productApplicationService.create(createProductRequest));
    }

    @GetMapping
    public String get()
    {
        return "Deneme";
    }
}

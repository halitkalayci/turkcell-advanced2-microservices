package com.turkcell.catalogservice.interfaces.web.controller;

import com.turkcell.catalogservice.application.product.dto.CreateProductRequest;
import com.turkcell.catalogservice.application.product.dto.CreatedProductResponse;
import com.turkcell.catalogservice.application.product.dto.GetByIdProductResponse;
import com.turkcell.catalogservice.application.product.service.ProductApplicationService;
import com.turkcell.catalogservice.domain.Product;
import io.micrometer.observation.annotation.Observed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v2/products")
@RestController
@Observed(name="products-controller")
public class ProductsController {
    private final ProductApplicationService productApplicationService;

    public ProductsController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreatedProductResponse> addProduct(@RequestBody CreateProductRequest createProductRequest) {
        return ResponseEntity.ok(this.productApplicationService.create(createProductRequest));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetByIdProductResponse> get(@PathVariable("id") UUID id)
    {
        return ResponseEntity.ok(this.productApplicationService.getByID(id));
    }
}

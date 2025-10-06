package com.turkcell.catalogservice;

import com.turkcell.catalogservice.contract.v1.api.CatalogApi;
import com.turkcell.catalogservice.contract.v1.model.ListProducts200Response;
import com.turkcell.catalogservice.contract.v1.model.ProductCreateRequest;
import com.turkcell.catalogservice.contract.v1.model.ProductResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CatalogController implements CatalogApi {
    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductCreateRequest productCreateRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Deprecation", "true");
        headers.set("Sunset", "Tue, 31 Dec 2025 23:59:59 GMT");

        return new ResponseEntity<>(new ProductResponse(), headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductResponse> getProductById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<ListProducts200Response> listProducts(Integer page, Integer size) {
        return null;
    }
}

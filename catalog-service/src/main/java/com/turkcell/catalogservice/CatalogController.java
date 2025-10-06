package com.turkcell.catalogservice;

import com.turkcell.catalogservice.contract.api.CatalogApi;
import com.turkcell.catalogservice.contract.model.ListProducts200Response;
import com.turkcell.catalogservice.contract.model.ProductCreateRequest;
import com.turkcell.catalogservice.contract.model.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CatalogController implements CatalogApi {
    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductCreateRequest productCreateRequest) {
        return null;
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

package com.turkcell.catalogservice;

import com.turkcell.catalogservice.contract.v2.api.CatalogV2Api;
import com.turkcell.catalogservice.contract.v2.model.ListProducts200Response;
import com.turkcell.catalogservice.contract.v2.model.ProductCreateRequestV2;
import com.turkcell.catalogservice.contract.v2.model.ProductResponseV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CatalogV2Controller implements CatalogV2Api {

    @Override
    public ResponseEntity<ProductResponseV2> createProduct(ProductCreateRequestV2 productCreateRequestV2) {
        return null;
    }

    @Override
    public ResponseEntity<ProductResponseV2> getProductById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<ListProducts200Response> listProducts(Integer page, Integer size) {
        return null;
    }
}

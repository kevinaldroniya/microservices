package com.domain.expansion.productservice.service;

import com.domain.expansion.productservice.dto.ProductRequest;
import com.domain.expansion.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    public void createProduct(ProductRequest productRequest);

    public List<ProductResponse> getAllProducts();
}

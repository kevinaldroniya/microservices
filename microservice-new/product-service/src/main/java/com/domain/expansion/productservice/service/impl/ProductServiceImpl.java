package com.domain.expansion.productservice.service.impl;

import com.domain.expansion.productservice.dto.ProductRequest;
import com.domain.expansion.productservice.dto.ProductResponse;
import com.domain.expansion.productservice.dto.RequestEditProduct;
import com.domain.expansion.productservice.model.Product;
import com.domain.expansion.productservice.repository.ProductRepository;
import com.domain.expansion.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductResponse> productResponses = products.stream().map(
                this::mapToProductResponse
        ).toList();

        return productResponses;
    }

    @Transactional
    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));
        log.info("Product {} has been deleted", product.getName());
        productRepository.delete(product);
    }

    @Transactional
    @Override
    public ProductResponse updateProduct(RequestEditProduct requestEditProduct) {
        Product product = productRepository.findById(requestEditProduct.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        product.setPrice(requestEditProduct.getPrice());
        product.setDescription(requestEditProduct.getDescription());
        product.setName(requestEditProduct.getName());
        productRepository.save(product);

        return ProductResponse.builder().id(product.getId()).name(product.getName()).description(product.getDescription()).price(product.getPrice()).build();
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

        return response;
    }


}

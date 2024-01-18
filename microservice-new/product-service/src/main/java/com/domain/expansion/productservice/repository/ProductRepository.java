package com.domain.expansion.productservice.repository;

import com.domain.expansion.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}

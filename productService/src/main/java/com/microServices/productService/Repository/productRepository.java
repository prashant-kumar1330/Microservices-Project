package com.microServices.productService.Repository;

import com.microServices.productService.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface productRepository extends MongoRepository<Product, Integer> {
}

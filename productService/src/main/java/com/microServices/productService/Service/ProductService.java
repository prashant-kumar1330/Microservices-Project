package com.microServices.productService.Service;

import com.microServices.productService.Repository.productRepository;
import com.microServices.productService.dto.ProductRequest;
import com.microServices.productService.dto.ProductResponse;
import com.microServices.productService.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    private final productRepository myProductRepository;

    public ProductService(productRepository myProductRepository) {
        this.myProductRepository = myProductRepository;
    }


    public List<ProductResponse> getAllProduct() {
        List<Product> productList = myProductRepository.findAll();
        //testc
        return productList.stream().map((ProductService::mapProductToDTO)).toList();
    }

    private static ProductResponse mapProductToDTO(Product product) {
        return ProductResponse.builder().name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice()).build();

    }

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder().id(productRequest.getId())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();

        myProductRepository.save(product);
    }
}

package com.microServices.productService.Controller;

import com.microServices.productService.Service.ProductService;
import com.microServices.productService.dto.ProductRequest;
import com.microServices.productService.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class productController {


    private final ProductService productService;


    public productController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProduct() {
        return productService.getAllProduct();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }


}

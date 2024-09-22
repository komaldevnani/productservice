package com.scaler.productservice.controllers;

import com.scaler.productservice.dtos.ProductResponseDto;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public String getAllProducts() {
        return "Hello World";
    }

    @GetMapping("/product/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        return ProductResponseDto.from(product);
    }


}

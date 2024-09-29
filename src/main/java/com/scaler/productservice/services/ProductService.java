package com.scaler.productservice.services;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product getProductById(Long id) throws ProductNotFoundException;

    public List<Product> getAllProducts();

    public Product createProduct(String title, String description, Double price, String imageUrl, String categoryName);

    public Product partialUpdateProduct(Long id, String title, String description, Double price, String imageUrl, String categoryName);
}

package com.scaler.productservice.services;

import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public Product getProductById(Long id);

}

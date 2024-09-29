package com.scaler.productservice.services;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.dtos.FakeStoreProductResponseDto;
import com.scaler.productservice.dtos.FakeStoreRequestDto;
import com.scaler.productservice.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductResponseDto responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductResponseDto.class
        );
        if(responseDto == null) {
            throw new ProductNotFoundException("Product with id: " + id + " not found");
        }
        return responseDto.toProduct();

    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductResponseDto[] responseDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductResponseDto[].class
        );
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductResponseDto responseDto : responseDtos) {
            products.add(responseDto.toProduct());
        }
        return products;
    }

    public Product createProduct(String title, String description, Double price,
                                 String imageUrl, String categoryName) {

        FakeStoreRequestDto fakeStoreRequestDto = new FakeStoreRequestDto();
        fakeStoreRequestDto.setTitle(title);
        fakeStoreRequestDto.setDescription(description);
        fakeStoreRequestDto.setPrice(price);
        fakeStoreRequestDto.setImage(imageUrl);
        fakeStoreRequestDto.setCategory(categoryName);

        FakeStoreProductResponseDto fakeStoreProductResponseDto = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreRequestDto,
                FakeStoreProductResponseDto.class
        );
        return fakeStoreProductResponseDto.toProduct();

    }

    public Product partialUpdateProduct(Long id, String title, String description, Double price, String imageUrl, String categoryName) {
        FakeStoreRequestDto fakeStoreRequestDto = new FakeStoreRequestDto();
        FakeStoreProductResponseDto fakeStoreProductResponseDto = restTemplate.patchForObject(
                "https://fakestoreapi.com/product/" + id,
                fakeStoreRequestDto,
                FakeStoreProductResponseDto.class
        );
        return fakeStoreProductResponseDto.toProduct();
    }
}

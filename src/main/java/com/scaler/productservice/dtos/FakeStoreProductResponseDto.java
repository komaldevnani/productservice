package com.scaler.productservice.dtos;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class FakeStoreProductResponseDto {
    private long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

    public Product toProduct() {
        Product product = new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(Double.valueOf(this.price));
        product.setImageUrl(this.image);

        Category category1 = new Category();
        category1.setName(this.category);
        product.setCategory(category1);
        return product;
    }

}

package com.matkap.ecommerce.dto.responseDto.product;

import com.matkap.ecommerce.model.product.ProductCategory;

public class VariationResponseDto {

    private Long id;
    private ProductCategory productCategory;
    private String name;

    public VariationResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

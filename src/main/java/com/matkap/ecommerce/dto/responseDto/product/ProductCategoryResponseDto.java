package com.matkap.ecommerce.dto.responseDto.product;

import com.matkap.ecommerce.model.product.ProductCategory;

import java.util.List;

public class ProductCategoryResponseDto {

    private Long productCategoryId;
    private String categoryName;

    private ProductCategory parentCategory;

    private List<ProductCategory> childCategory;


    public ProductCategoryResponseDto(Long productCategoryId, String categoryName, ProductCategory parentCategory, List<ProductCategory> childCategory) {
        this.productCategoryId = productCategoryId;
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.childCategory = childCategory;
    }

    public ProductCategoryResponseDto() {
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ProductCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ProductCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<ProductCategory> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(List<ProductCategory> childCategory) {
        this.childCategory = childCategory;
    }
}

package com.matkap.ecommerce.dto.requestDto.product;

import jakarta.validation.constraints.NotBlank;

public class ProductCategoryRequestDto {
    @NotBlank(message = "categoryName cannot be blank")
    private String categoryName;

    private Long parentCategoryId;


    public ProductCategoryRequestDto(String categoryName, Long parentCategoryId) {
        this.categoryName = categoryName;
        this.parentCategoryId = parentCategoryId;
    }

    public ProductCategoryRequestDto() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}

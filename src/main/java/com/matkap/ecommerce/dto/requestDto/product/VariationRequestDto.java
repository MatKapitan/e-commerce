package com.matkap.ecommerce.dto.requestDto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VariationRequestDto {

    @NotNull(message = "categoryId cannot be null")
    private Long categoryId;
    @NotBlank(message = "name cannot be blank")
    private String name;

    public VariationRequestDto() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

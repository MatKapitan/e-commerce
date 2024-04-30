package com.matkap.ecommerce.dto.responseDto.product;

public class VariationResponseDto {

    private Long id;
    private String categoryName;
    private String name;

    public VariationResponseDto(Long id, String categoryName, String name) {
        this.id = id;
        this.categoryName = categoryName;
        this.name = name;
    }

    public VariationResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

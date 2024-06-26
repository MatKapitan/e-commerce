package com.matkap.ecommerce.dto.responseDto.product;

public class ProductResponseDto {


    private Long id;
    private String name;

    private String description;

    private String productImage;

    private String categoryName;

    public ProductResponseDto(Long id, String name, String description, String productImage, String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productImage = productImage;
        this.categoryName = categoryName;
    }

    public ProductResponseDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

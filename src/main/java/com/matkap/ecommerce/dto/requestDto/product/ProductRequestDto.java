package com.matkap.ecommerce.dto.requestDto.product;

public class ProductRequestDto {

    private String name;

    private String description;

    private String product_image;

    private Long product_category_id;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String name, String description, String product_image) {
        this.name = name;
        this.description = description;
        this.product_image = product_image;
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

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public Long getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(Long product_category_id) {
        this.product_category_id = product_category_id;
    }
}

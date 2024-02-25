package com.matkap.ecommerce.dto.requestDto.product;

public class VariationRequestDto {

    private Long category_id;
    private String name;

    public VariationRequestDto() {
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

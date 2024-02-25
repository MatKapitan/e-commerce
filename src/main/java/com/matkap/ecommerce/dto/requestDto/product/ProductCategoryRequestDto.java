package com.matkap.ecommerce.dto.requestDto.product;

public class ProductCategoryRequestDto {

    private String category_name;

    private Long parent_category_id;







    public ProductCategoryRequestDto() {
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Long getParent_category_id() {
        return parent_category_id;
    }

    public void setParent_category_id(Long parent_category_id) {
        this.parent_category_id = parent_category_id;
    }
}

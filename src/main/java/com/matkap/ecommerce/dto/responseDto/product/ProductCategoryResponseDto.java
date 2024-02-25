package com.matkap.ecommerce.dto.responseDto.product;

import com.matkap.ecommerce.model.product.ProductCategory;

import java.util.List;

public class ProductCategoryResponseDto {

    private Long product_category_id;
    private String category_name;

    private ProductCategory parent_category;

    private List<ProductCategory> child_category;




    public ProductCategoryResponseDto() {
    }

    public String getCategory_name() {
        return category_name;
    }

    public Long getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(Long product_category_id) {
        this.product_category_id = product_category_id;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public ProductCategory getParent_category() {
        return parent_category;
    }

    public void setParent_category(ProductCategory parent_category) {
        this.parent_category = parent_category;
    }

    public List<ProductCategory> getChild_category() {
        return child_category;
    }

    public void setChild_category(List<ProductCategory> child_category) {
        this.child_category = child_category;
    }
}

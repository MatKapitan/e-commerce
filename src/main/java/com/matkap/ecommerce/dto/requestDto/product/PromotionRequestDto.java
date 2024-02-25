package com.matkap.ecommerce.dto.requestDto.product;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class PromotionRequestDto {

    private String name;
    private String description;
    private Double discount_rate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp start_date;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp end_date;

    private Long productCategoryId;


    public PromotionRequestDto() {
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

    public Double getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(Double discount_rate) {
        this.discount_rate = discount_rate;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }
}

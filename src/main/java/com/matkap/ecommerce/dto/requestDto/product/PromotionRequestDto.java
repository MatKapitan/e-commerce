package com.matkap.ecommerce.dto.requestDto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;

public class PromotionRequestDto {

    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "description cannot be blank")
    private String description;
    @NotNull(message = "discountRate cannot be null")
    @Positive(message = "discountRate must be positive")
    private Double discountRate;
    @NotNull(message = "startDate cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp startDate;
    @NotNull(message = "endDate cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp endDate;



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

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

}

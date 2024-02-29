package com.matkap.ecommerce.dto.requestDto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VariationOptionRequestDto {

    @NotNull(message = "variationId cannot be null")
    private Long variationId;
    @NotBlank(message = "value cannot be blank")
    private String value;


    public VariationOptionRequestDto() {
    }

    public Long getVariationId() {
        return variationId;
    }

    public void setVariationId(Long variationId) {
        this.variationId = variationId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

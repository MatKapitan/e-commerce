package com.matkap.ecommerce.dto.responseDto.product;

import com.matkap.ecommerce.model.product.Variation;

public class VariationOptionResponseDto {

    private Long id;
    private Variation variation;
    private String value;


    public VariationOptionResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Variation getVariation() {
        return variation;
    }

    public void setVariation(Variation variation) {
        this.variation = variation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

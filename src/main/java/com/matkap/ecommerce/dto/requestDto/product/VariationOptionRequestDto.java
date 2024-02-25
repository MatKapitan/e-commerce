package com.matkap.ecommerce.dto.requestDto.product;

public class VariationOptionRequestDto {

    private Long variation_id;
    private String value;


    public VariationOptionRequestDto() {
    }

    public Long getVariation_id() {
        return variation_id;
    }

    public void setVariation_id(Long variation_id) {
        this.variation_id = variation_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

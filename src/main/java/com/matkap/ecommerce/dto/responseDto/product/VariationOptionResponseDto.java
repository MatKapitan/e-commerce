package com.matkap.ecommerce.dto.responseDto.product;

public class VariationOptionResponseDto {

    private Long id;
    private String variationName;
    private String value;


    public VariationOptionResponseDto(Long id, String variationName, String value) {
        this.id = id;
        this.variationName = variationName;
        this.value = value;
    }

    public VariationOptionResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVariationName() {
        return variationName;
    }

    public void setVariationName(String variationName) {
        this.variationName = variationName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

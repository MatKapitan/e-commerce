package com.matkap.ecommerce.service.product;

import com.matkap.ecommerce.dto.requestDto.product.VariationOptionRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.VariationOptionResponseDto;
import com.matkap.ecommerce.model.product.VariationOption;

import java.util.List;

public interface VariationOptionService {



    public VariationOptionResponseDto createVariationOption(VariationOptionRequestDto variationOptionRequestDto);
    public List<VariationOptionResponseDto> getVariationOptions();
    public VariationOptionResponseDto getVariationOptionById(Long validationOptionId);
    public VariationOption getVariationOption(Long validationOptionId);
    public void deleteVariationOption(Long validationOptionId);
    public VariationOptionResponseDto editVariationOption(Long validationOptionId, VariationOptionRequestDto variationOptionRequestDto);
}

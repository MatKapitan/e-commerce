package com.matkap.ecommerce.service.product;


import com.matkap.ecommerce.dto.requestDto.product.VariationRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.VariationResponseDto;
import com.matkap.ecommerce.model.product.Variation;

import java.util.List;

public interface VariationService {


    public VariationResponseDto createVariation(VariationRequestDto variationRequestDto);
    public List<VariationResponseDto> getVariations();
    public VariationResponseDto getVariationById(Long validationId);
    public Variation getVariation(Long validationId);
    public VariationResponseDto deleteVariation(Long validationId);
    public VariationResponseDto editVariation(Long validationId, VariationRequestDto variationRequestDto);
}

package com.matkap.ecommerce.service.product.impl;

import com.matkap.ecommerce.dto.Mapper;
import com.matkap.ecommerce.dto.requestDto.product.VariationRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.VariationResponseDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.product.ProductCategory;
import com.matkap.ecommerce.model.product.Variation;
import com.matkap.ecommerce.repository.product.VariationRepository;
import com.matkap.ecommerce.service.product.ProductCategoryService;
import com.matkap.ecommerce.service.product.VariationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VariationServiceImpl implements VariationService {

    private final VariationRepository variationRepository;

    private final ProductCategoryService productCategoryService;

    public VariationServiceImpl(VariationRepository variationRepository, ProductCategoryService productCategoryService) {
        this.variationRepository = variationRepository;
        this.productCategoryService = productCategoryService;
    }

    @Override
    public VariationResponseDto createVariation(VariationRequestDto variationRequestDto) {
        Variation variation = new Variation();
        variation.setName(variationRequestDto.getName());
        if (variationRequestDto.getCategoryId() == null){
            throw new IllegalArgumentException("Variation has to have a category");
        }
        ProductCategory productCategory = productCategoryService.getProductCategory(variationRequestDto.getCategoryId());
        variation.setProductCategory(productCategory);
        variationRepository.save(variation);
        return Mapper.variationToVariationResponseDto(variation);
    }

    @Override
    public List<VariationResponseDto> getVariations() {
        List<Variation> variations = new ArrayList<>(variationRepository.findAll());
        return Mapper.variationsToVariationsResponseDtos(variations);
    }

    @Override
    public VariationResponseDto getVariationById(Long validationId) {
        return Mapper.variationToVariationResponseDto(getVariation(validationId));
    }

    @Override
    public Variation getVariation(Long validationId) {
        return variationRepository.findById(validationId).orElseThrow(
                () -> new EntityNotFoundException(validationId, Variation.class));
    }

    @Override
    public void deleteVariation(Long validationId) {
        Variation variation = getVariation(validationId);
        variationRepository.delete(variation);
    }

    @Override
    public VariationResponseDto editVariation(Long validationId, VariationRequestDto variationRequestDto) {
        Variation variationToEdit = getVariation(validationId);
        variationToEdit.setName(variationRequestDto.getName());
        if (variationRequestDto.getCategoryId() == null){
            throw new IllegalArgumentException("Variation has to have a category");
        }
        ProductCategory productCategory = productCategoryService.getProductCategory(variationRequestDto.getCategoryId());
        variationToEdit.setProductCategory(productCategory);

        return Mapper.variationToVariationResponseDto(variationToEdit);
    }
}

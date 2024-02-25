package com.matkap.ecommerce.service.product.impl;

import com.matkap.ecommerce.dto.Mapper;
import com.matkap.ecommerce.dto.requestDto.product.VariationOptionRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.VariationOptionResponseDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.product.Variation;
import com.matkap.ecommerce.model.product.VariationOption;
import com.matkap.ecommerce.repository.product.VariationOptionRepository;
import com.matkap.ecommerce.service.product.VariationOptionService;
import com.matkap.ecommerce.service.product.VariationService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VariationOptionServiceImpl implements VariationOptionService {

private final VariationOptionRepository variationOptionRepository;

private final VariationService variationService;

    public VariationOptionServiceImpl(VariationOptionRepository variationOptionRepository, VariationService variationService) {
        this.variationOptionRepository = variationOptionRepository;
        this.variationService = variationService;
    }


    @Override
    public VariationOptionResponseDto createVariationOption(VariationOptionRequestDto variationOptionRequestDto) {
        VariationOption variationOption = new VariationOption();
        variationOption.setValue(variationOptionRequestDto.getValue());
        if (variationOptionRequestDto.getVariation_id() != null){
            Variation variation = variationService.getVariation(variationOptionRequestDto.getVariation_id());
            variationOption.setVariation(variation);
        }
        variationOptionRepository.save(variationOption);
        return Mapper.variationOptionToVariationOptionResponseDto(variationOption);
    }

    @Override
    public List<VariationOptionResponseDto> getVariationOptions() {
        List<VariationOption> variationOptions = variationOptionRepository.findAll();
        return Mapper.variationOptionsToVariationOptionResponseDtos(variationOptions);
    }

    @Override
    public VariationOptionResponseDto getVariationOptionById(Long validationOptionId) {
        VariationOption variationOption = getVariationOption(validationOptionId);
        return Mapper.variationOptionToVariationOptionResponseDto(variationOption);
    }

    @Override
    public VariationOption getVariationOption(Long validationOptionId) {
        return variationOptionRepository.findById(validationOptionId).orElseThrow(
                () -> new EntityNotFoundException(validationOptionId, VariationOption.class));
    }

    @Override
    public VariationOptionResponseDto deleteVariationOption(Long validationOptionId) {
        VariationOption variationOption = getVariationOption(validationOptionId);
        variationOptionRepository.delete(variationOption);
        return Mapper.variationOptionToVariationOptionResponseDto(variationOption);
    }

    @Override
    public VariationOptionResponseDto editVariationOption(Long validationOptionId,
                                                          VariationOptionRequestDto variationOptionRequestDto) {
        VariationOption variationOption = getVariationOption(validationOptionId);
        variationOption.setValue(variationOptionRequestDto.getValue());
        if (variationOptionRequestDto.getVariation_id() != null){
            Variation variation = variationService.getVariation(variationOptionRequestDto.getVariation_id());
            variationOption.setVariation(variation);
        }
        return Mapper.variationOptionToVariationOptionResponseDto(variationOption);
    }
}

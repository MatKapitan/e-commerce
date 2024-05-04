package com.matkap.ecommerce.service.product.impl;

import com.matkap.ecommerce.dto.requestDto.product.VariationOptionRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.VariationOptionResponseDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.product.Variation;
import com.matkap.ecommerce.model.product.VariationOption;
import com.matkap.ecommerce.repository.product.VariationOptionRepository;
import com.matkap.ecommerce.service.product.VariationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class VariationOptionServiceImplTest {


    @Mock
    private VariationOptionRepository variationOptionRepository;

    @Mock
    private VariationService variationService;

    @InjectMocks
    private VariationOptionServiceImpl variationOptionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_create_variation_option() {
        Variation variation = new Variation(null, "Color");
        VariationOption variationOption = new VariationOption(variation, "Red");
        VariationOptionRequestDto variationOptionRequestDto = new VariationOptionRequestDto(1L, "Red");

        when(variationService.getVariation(anyLong())).thenReturn(variation);
        when(variationOptionRepository.save(variationOption)).thenReturn(variationOption);

        VariationOptionResponseDto result = variationOptionService.createVariationOption(variationOptionRequestDto);
        assertEquals(variationOptionRequestDto.getValue(), result.getValue());
    }

    @Test
    public void should_throw_illegal_argument_exception_when_variation_is_null() {
        VariationOptionRequestDto variationOptionRequestDto = new VariationOptionRequestDto(null, "Red");
        assertThrows(IllegalArgumentException.class, () -> variationOptionService.createVariationOption(variationOptionRequestDto));
    }

    @Test
    public void should_return_list_of_variation_options() {
        Variation variation = new Variation(null, "Color");
        List<VariationOption> variationOptionList = Arrays.asList(new VariationOption(variation, "Red"),
                new VariationOption(variation, "Red"));
        when(variationOptionRepository.findAll()).thenReturn(variationOptionList);

        List<VariationOptionResponseDto> result = variationOptionService.getVariationOptions();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(variationOptionList.get(0).getValue(), result.get(0).getValue());
        assertEquals(variationOptionList.get(0).getVariation().getName(), result.get(0).getVariationName());
        //
        assertEquals(variationOptionList.get(1).getValue(), result.get(1).getValue());
        assertEquals(variationOptionList.get(1).getVariation().getName(), result.get(1).getVariationName());
    }

    @Test
    public void should_return_variation_option_by_id() {
        Variation variation = new Variation(null, "Color");
        VariationOption variationOption = new VariationOption(variation, "Red");

        when(variationOptionRepository.findById(anyLong())).thenReturn(Optional.of(variationOption));

        VariationOptionResponseDto result = variationOptionService.getVariationOptionById(anyLong());

        assertEquals(variationOption.getValue(), result.getValue());
        assertEquals(variationOption.getVariation().getName(), result.getVariationName());
    }

    @Test
    public void should_throw_entity_not_found_exception_if_variation_option_does_not_exist() {
        EntityNotFoundException result = assertThrows(EntityNotFoundException.class, () -> variationOptionService.getVariationOptionById(anyLong()));

        assertEquals(result.getMessage(), "The variationoption with id '0' does not exist in our records");
    }

    @Test
    public void should_delete_variation_option() {
        Variation variation = new Variation(null, "Color");
        VariationOption variationOption = new VariationOption(variation, "Red");

        when(variationOptionRepository.findById(anyLong())).thenReturn(Optional.of(variationOption));

        variationOptionService.deleteVariationOption(anyLong());
        verify(variationOptionRepository, times(1)).delete(variationOption);
    }

    @Test
    public void should_successfully_edit_a_variation_option() {
        Variation variation = new Variation(null, "Color");
        VariationOption variationOption = new VariationOption(variation, "Red");
        VariationOptionRequestDto variationOptionRequestDto = new VariationOptionRequestDto(1L, "Blue");
        when(variationService.getVariation(anyLong())).thenReturn(variation);
        when(variationOptionRepository.findById(anyLong())).thenReturn(Optional.of(variationOption));

        VariationOptionResponseDto result = variationOptionService.editVariationOption(anyLong(), variationOptionRequestDto);

        assertEquals(variationOptionRequestDto.getValue(), result.getValue());

    }
}
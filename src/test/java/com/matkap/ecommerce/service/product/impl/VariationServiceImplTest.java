package com.matkap.ecommerce.service.product.impl;

import com.matkap.ecommerce.dto.requestDto.product.VariationRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.VariationResponseDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.product.ProductCategory;
import com.matkap.ecommerce.model.product.Variation;
import com.matkap.ecommerce.repository.product.VariationRepository;
import com.matkap.ecommerce.service.product.ProductCategoryService;
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

class VariationServiceImplTest {

    @Mock
    private VariationRepository variationRepository;

    @Mock
    private ProductCategoryService productCategoryService;

    @InjectMocks
    private VariationServiceImpl variationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_create_variation() {
        ProductCategory productCategory = new ProductCategory(null, null, "variation");
        VariationRequestDto variationRequestDto = new VariationRequestDto(1L, "Color");
        Variation variation = new Variation(productCategory, "Color");
        when(productCategoryService.getProductCategory(anyLong())).thenReturn(productCategory);
        when(variationRepository.save(variation)).thenReturn(variation);

        VariationResponseDto result = variationService.createVariation(variationRequestDto);

        assertEquals(variationRequestDto.getName(), result.getName());
        assertEquals(variation.getProductCategory().getCategoryName(), result.getCategoryName());

    }

    @Test
    public void should_throw_illegal_argument_exception_then_product_category_is_null() {
        VariationRequestDto variationRequestDto = new VariationRequestDto(null, "Color");
        assertThrows(IllegalArgumentException.class, () -> variationService.createVariation(variationRequestDto));
    }

    @Test
    public void should_return_list_of_variations() {
        ProductCategory productCategory = new ProductCategory(null, null, "variation");
        List<Variation> variationList = Arrays.asList(new Variation(productCategory, "Color"),
                new Variation(productCategory, "Color"));
        when(variationRepository.findAll()).thenReturn(variationList);

        List<VariationResponseDto> result = variationService.getVariations();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(variationList.get(0).getName(), result.get(0).getName());
        assertEquals(variationList.get(0).getProductCategory().getCategoryName(), result.get(0).getCategoryName());
        //
        assertEquals(variationList.get(1).getName(), result.get(1).getName());
        assertEquals(variationList.get(1).getProductCategory().getCategoryName(), result.get(1).getCategoryName());
    }

    @Test
    public void should_return_variation_by_id() {
        ProductCategory productCategory = new ProductCategory(null, null, "variation");
        Variation variation = new Variation(productCategory, "Color");
        when(variationRepository.findById(anyLong())).thenReturn(Optional.of(variation));

        VariationResponseDto result = variationService.getVariationById(anyLong());

        assertEquals(variation.getName(), result.getName());
        assertEquals(variation.getProductCategory().getCategoryName(), result.getCategoryName());
    }

    @Test
    public void should_throw_entity_not_found_exception_if_variation_does_not_exist() {
        EntityNotFoundException result = assertThrows(EntityNotFoundException.class, () -> variationService.getVariation(anyLong()));

        assertEquals(result.getMessage(), "The variation with id '0' does not exist in our records");
    }

    @Test
    public void should_delete_variation() {
        ProductCategory productCategory = new ProductCategory(null, null, "clothing");
        Variation variation = new Variation(productCategory, "Color");
        when(variationRepository.findById(anyLong())).thenReturn(Optional.of(variation));

        variationService.deleteVariation(anyLong());
        verify(variationRepository, times(1)).delete(variation);
    }

    @Test
    public void should_successfully_edit_a_variation() {
        ProductCategory productCategory = new ProductCategory(null, null, "clothing");
        VariationRequestDto variationRequestDto = new VariationRequestDto(1L, "Color");
        Variation variation = new Variation(productCategory, "Color");
        when(productCategoryService.getProductCategory(anyLong())).thenReturn(productCategory);
        when(variationRepository.findById(anyLong())).thenReturn(Optional.of(variation));

        VariationResponseDto result = variationService.editVariation(anyLong(), variationRequestDto);

        assertEquals(variationRequestDto.getName(), result.getName());

    }
}
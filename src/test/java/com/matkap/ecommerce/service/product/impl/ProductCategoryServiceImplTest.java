package com.matkap.ecommerce.service.product.impl;

import com.matkap.ecommerce.dto.requestDto.product.ProductCategoryRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductCategoryResponseDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.product.ProductCategory;
import com.matkap.ecommerce.repository.product.ProductCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductCategoryServiceImplTest {




    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    private ProductCategoryServiceImpl productCategoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_product_category(){
        ProductCategoryRequestDto dto = new ProductCategoryRequestDto("clothing", null);
        ProductCategory productCategory = new ProductCategory(null, null,"clothing");
        when(productCategoryRepository.save(productCategory)).thenReturn(productCategory);

        ProductCategoryResponseDto result = productCategoryService.createProductCategory(dto);

        assertEquals(dto.getCategoryName(), result.getCategoryName());
        assertNull(dto.getParentCategoryId());
    }
    @Test
    public void should_successfully_save_a_product_category_with_parent(){
        ProductCategory productCategoryParent = new ProductCategory(null, null,"clothing");
        ProductCategoryRequestDto dto = new ProductCategoryRequestDto("male_clothing", 1L);
        ProductCategory productCategoryChild = new ProductCategory(productCategoryParent, null,"male_clothing");
        when(productCategoryRepository.save(productCategoryChild)).thenReturn(productCategoryChild);
        when(productCategoryRepository.findById(anyLong())).thenReturn(Optional.of(productCategoryParent));

        ProductCategoryResponseDto result = productCategoryService.createProductCategory(dto);

        assertEquals(dto.getCategoryName(), result.getCategoryName());
        assertEquals(productCategoryParent, result.getParentCategory());
        verify(productCategoryRepository, times(1)).findById(anyLong());
    }

    @Test
    public void should_return_product_category_by_id(){
        ProductCategory productCategory = new ProductCategory(null, null,"clothing");
        when(productCategoryRepository.findById(anyLong())).thenReturn(Optional.of(productCategory));

        ProductCategoryResponseDto result = productCategoryService.getProductCategoryById(anyLong());

        assertEquals(productCategory.getCategoryName(), result.getCategoryName());
        assertEquals(productCategory.getChildren_category(), result.getChildCategory());
    }

    @Test
    public void should_throw_entity_not_found_exception_if_product_category_does_not_exist(){
        EntityNotFoundException result = assertThrows(EntityNotFoundException.class, () -> productCategoryService.getProductCategoryById(anyLong()));

        assertEquals(result.getMessage(), "The productcategory with id '0' does not exist in our records" );
    }

    @Test
    public void should_return_all_product_categories(){
        List<ProductCategory> productCategoryList = Arrays.asList(
                new ProductCategory(null, null, "clothing"),
                new ProductCategory(null, null, "male_clothing"));
        when(productCategoryRepository.findAll()).thenReturn(productCategoryList);

        List<ProductCategoryResponseDto> result = productCategoryService.getProductCategories();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(productCategoryList.get(0).getCategoryName(), result.get(0).getCategoryName());
        assertEquals(productCategoryList.get(0).getId(), result.get(0).getProductCategoryId());
    }

    @Test
    public void should_delete_product_category(){
        ProductCategory productCategory = new ProductCategory(null, null, "clothing");
        when(productCategoryRepository.findById(anyLong())).thenReturn(Optional.of(productCategory));

        productCategoryService.deleteProductCategory(anyLong());
        verify(productCategoryRepository, times(1)).delete(productCategory);
    }

    @Test
    public void should_successfully_edit_a_product_category(){
        ProductCategory productCategoryToEdit = new ProductCategory(null, null, "clothing");
        when(productCategoryRepository.findById(anyLong())).thenReturn(Optional.of(productCategoryToEdit));
        ProductCategoryRequestDto dto = new ProductCategoryRequestDto("female_clothing", null);

        ProductCategoryResponseDto result = productCategoryService.editProductCategory(anyLong(), dto);

        assertEquals(dto.getCategoryName(), result.getCategoryName());
        assertEquals(dto.getParentCategoryId(), result.getParentCategory());
    }



}
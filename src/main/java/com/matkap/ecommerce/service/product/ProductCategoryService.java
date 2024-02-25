package com.matkap.ecommerce.service.product;

import com.matkap.ecommerce.dto.requestDto.product.ProductCategoryRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductCategoryResponseDto;
import com.matkap.ecommerce.model.product.ProductCategory;

import java.util.List;

public interface ProductCategoryService {


    public ProductCategoryResponseDto createProductCategory(ProductCategoryRequestDto productCategoryRequestDto);
    public List<ProductCategoryResponseDto> getProductCategories();
    public ProductCategoryResponseDto getProductCategoryById(Long productCategoryId);
    public ProductCategory getProductCategory(Long productCategoryId);
    public ProductCategoryResponseDto deleteProductCategory(Long productCategoryId);
    public ProductCategoryResponseDto editProductCategory(Long productCategoryId, ProductCategoryRequestDto productCategoryRequestDto);
}

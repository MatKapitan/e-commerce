package com.matkap.ecommerce.service.product.impl;

import com.matkap.ecommerce.dto.Mapper;
import com.matkap.ecommerce.dto.requestDto.product.ProductCategoryRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductCategoryResponseDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.product.ProductCategory;
import com.matkap.ecommerce.repository.product.ProductCategoryRepository;
import com.matkap.ecommerce.service.product.ProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {


    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public ProductCategoryResponseDto createProductCategory(ProductCategoryRequestDto productCategoryRequestDto) {
        ProductCategory productCategory  = new ProductCategory();
        productCategory.setCategory_name(productCategoryRequestDto.getCategoryName());
        if (productCategoryRequestDto.getParentCategoryId() != null) {
            productCategory.setParent_category(getProductCategory(productCategoryRequestDto.getParentCategoryId()));
        }
        productCategoryRepository.save(productCategory);
        return Mapper.productCategoryToProductCategoryResponseDto(productCategory);
    }


    @Override
    public List<ProductCategoryResponseDto> getProductCategories() {
        List<ProductCategory> productCategories = new ArrayList<>(productCategoryRepository.findAll());
        return Mapper.productCategoriesToProductCategoriesResponseDtos(productCategories);
    }

    @Override
    public ProductCategoryResponseDto getProductCategoryById(Long productCategoryId) {
        ProductCategory productCategory = getProductCategory(productCategoryId);
        return Mapper.productCategoryToProductCategoryResponseDto(productCategory);
    }

    @Override
    public ProductCategory getProductCategory(Long productCategoryId) {
        return productCategoryRepository.findById(productCategoryId).orElseThrow(
                ()-> new EntityNotFoundException(productCategoryId, ProductCategory.class));
    }

    @Override
    public void deleteProductCategory(Long productCategoryId) {
        ProductCategory productCategory = getProductCategory(productCategoryId);
        productCategoryRepository.delete(productCategory);
    }

    @Override
    public ProductCategoryResponseDto editProductCategory(Long productCategoryId, ProductCategoryRequestDto productCategoryRequestDto) {
        ProductCategory productCategory = getProductCategory(productCategoryId);
        productCategory.setCategory_name(productCategoryRequestDto.getCategoryName());
        if(productCategoryRequestDto.getParentCategoryId() != null){
            productCategory.setParent_category(getProductCategory(productCategoryRequestDto.getParentCategoryId()));
        }
        return Mapper.productCategoryToProductCategoryResponseDto(productCategory);
    }
}

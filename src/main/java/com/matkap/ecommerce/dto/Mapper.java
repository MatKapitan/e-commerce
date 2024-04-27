package com.matkap.ecommerce.dto;

import com.matkap.ecommerce.dto.responseDto.product.*;
import com.matkap.ecommerce.model.product.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static ProductResponseDto productToProductResponseDto(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setProductImage(product.getProductImage());
        productResponseDto.setCategoryName(product.getProductCategory().getCategoryName());
        return productResponseDto;
    }

    public static List<ProductResponseDto> productsToProductsResponseDtos(List<Product> products){
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product : products){
            productResponseDtos.add(productToProductResponseDto(product));
        }
        return productResponseDtos;
    }

    public static ProductItemResponseDto productItemToProductItemResponseDto(ProductItem productItem){
        ProductItemResponseDto productItemResponseDto = new ProductItemResponseDto();
        productItemResponseDto.setId(productItem.getId());
        productItemResponseDto.setQtyInStock(productItem.getQtyInStock());
        productItemResponseDto.setProduct(productItem.getProduct().getName());
        productItemResponseDto.setSKU(productItem.getSku());
        productItemResponseDto.setPrice(productItem.getPrice());
        productItemResponseDto.setProductImage(productItem.getProductImage());
        productItemResponseDto.setVariationOptionValues(productItem.getVariationOptions().stream().map(x -> x.getValue()).collect(Collectors.toList()));
        return productItemResponseDto;
    }

    public static List<ProductItemResponseDto> productItemsToProductItemsResponseDtos(List<ProductItem> productItems){
        List<ProductItemResponseDto> productItemResponseDto = new ArrayList<>();
        for (ProductItem productItem : productItems){
            productItemResponseDto.add(productItemToProductItemResponseDto(productItem));
        }
        return productItemResponseDto;
    }

    public static ProductCategoryResponseDto productCategoryToProductCategoryResponseDto(ProductCategory productCategory){
        ProductCategoryResponseDto productCategoryResponseDto = new ProductCategoryResponseDto();
        productCategoryResponseDto.setProductCategoryId(productCategory.getId());
        productCategoryResponseDto.setCategoryName(productCategory.getCategoryName());
        productCategoryResponseDto.setParentCategory(productCategory.getParent_category());
        productCategoryResponseDto.setChildCategory(productCategory.getChildren_category());
        return productCategoryResponseDto;
    }

    public static List<ProductCategoryResponseDto> productCategoriesToProductCategoriesResponseDtos(List<ProductCategory> productCategories){
        List<ProductCategoryResponseDto> productCategoryResponseDtos = new ArrayList<>();
        for (ProductCategory productCategory : productCategories){
            productCategoryResponseDtos.add(productCategoryToProductCategoryResponseDto(productCategory));
        }
        return productCategoryResponseDtos;
    }

    public static VariationResponseDto variationToVariationResponseDto(Variation variation){
        VariationResponseDto variationResponseDto = new VariationResponseDto();
        variationResponseDto.setId(variation.getId());
        variationResponseDto.setCategoryName(variation.getProductCategory().getCategoryName()); //***********************************
        variationResponseDto.setName(variation.getName());
        return variationResponseDto;
    }

    public static List<VariationResponseDto> variationsToVariationsResponseDtos(List<Variation> variations) {
        List<VariationResponseDto> variationResponseDtos = new ArrayList<>();
        for (Variation variation : variations) {
            variationResponseDtos.add(variationToVariationResponseDto(variation));
        }
        return variationResponseDtos;
    }

    public static VariationOptionResponseDto variationOptionToVariationOptionResponseDto(VariationOption variationOption){
        VariationOptionResponseDto variationOptionResponseDto = new VariationOptionResponseDto();
        variationOptionResponseDto.setId(variationOption.getId());
        variationOptionResponseDto.setVariationName(variationOption.getVariation().getName());
        variationOptionResponseDto.setValue(variationOption.getValue());
        return variationOptionResponseDto;
    }

    public static List<VariationOptionResponseDto> variationOptionsToVariationOptionResponseDtos(List<VariationOption> variationOptions){
        List<VariationOptionResponseDto> variationOptionResponseDtos = new ArrayList<>();
        for (VariationOption variationOption : variationOptions){
            variationOptionResponseDtos.add(variationOptionToVariationOptionResponseDto(variationOption));
        }
        return variationOptionResponseDtos;
    }

}

package com.matkap.ecommerce.dto;

import com.matkap.ecommerce.dto.responseDto.product.*;
import com.matkap.ecommerce.model.product.*;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static ProductResponseDto productToProductResponseDto(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setProduct_image(product.getProduct_image());
        productResponseDto.setProductCategory(product.getProductCategory());
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
        productItemResponseDto.setQty_in_stock(productItem.getQty_in_stock());
        productItemResponseDto.setProduct(productItem.getProduct());
        productItemResponseDto.setSKU(productItem.getSKU());
        productItemResponseDto.setPrice(productItem.getPrice());
        productItemResponseDto.setProduct_image(productItem.getProduct_image());
        productItemResponseDto.setVariationOptions(productItem.getVariationOptions());
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
        productCategoryResponseDto.setProduct_category_id(productCategory.getId());
        productCategoryResponseDto.setCategory_name(productCategory.getCategory_name());
        productCategoryResponseDto.setParent_category(productCategory.getParent_category());
        productCategoryResponseDto.setChild_category(productCategory.getChildren_category());
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
        variationResponseDto.setProductCategory(variation.getProductCategory());
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
        variationOptionResponseDto.setVariation(variationOption.getVariation());
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

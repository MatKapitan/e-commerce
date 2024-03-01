package com.matkap.ecommerce.service.product;

import com.matkap.ecommerce.dto.requestDto.product.ProductItemRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductItemResponseDto;
import com.matkap.ecommerce.model.product.ProductItem;

import java.util.List;

public interface ProductItemService {

    public ProductItemResponseDto createProductItem(ProductItemRequestDto productItemRequestDto);
    public List<ProductItemResponseDto> getProductItems();
    public ProductItemResponseDto getProductItemById(Long productItemId);
    public ProductItem getProductItem(Long productItemId);
    public void deleteProductItem(Long productItemId);
    public ProductItemResponseDto editProductItem(Long productItemId, ProductItemRequestDto productItemRequestDto);

    public ProductItemResponseDto addVariationOptionToProductItem(Long product_item_id ,Long variation_option_id);
    public ProductItemResponseDto removeVariationOptionFromProductItem(Long product_item_id , Long variation_option_id);

    public boolean isProductInStock(Long id, Long qty);
    public void reduceProductInStock(Long id, Long qty);

}

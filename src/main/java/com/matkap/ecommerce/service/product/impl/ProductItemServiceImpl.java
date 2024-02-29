package com.matkap.ecommerce.service.product.impl;

import com.matkap.ecommerce.dto.Mapper;
import com.matkap.ecommerce.dto.requestDto.product.ProductItemRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductItemResponseDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.product.Product;
import com.matkap.ecommerce.model.product.ProductItem;
import com.matkap.ecommerce.model.product.VariationOption;
import com.matkap.ecommerce.repository.product.ProductItemRepository;
import com.matkap.ecommerce.service.product.ProductItemService;
import com.matkap.ecommerce.service.product.ProductService;
import com.matkap.ecommerce.service.product.VariationOptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductItemServiceImpl implements ProductItemService {

    private final ProductService productService;
    private final ProductItemRepository productItemRepository;

    private final VariationOptionService variationOptionService;

    public ProductItemServiceImpl(ProductService productService, ProductItemRepository productItemRepository, VariationOptionService variationOptionService) {
        this.productService = productService;
        this.productItemRepository = productItemRepository;
        this.variationOptionService = variationOptionService;
    }


    @Override
    public ProductItemResponseDto createProductItem(ProductItemRequestDto productItemRequestDto) {

        ProductItem productItem = new ProductItem();
        productItem.setSKU(productItemRequestDto.getSku());
        productItem.setQty_in_stock(productItemRequestDto.getQuantityInStock());
        productItem.setProduct_image(productItemRequestDto.getProductImage());
        productItem.setPrice(productItemRequestDto.getPrice());

        if(productItemRequestDto.getProductId() == null){
            throw new IllegalArgumentException("Product has to have a category");
        }
        Product product = productService.getProduct(productItemRequestDto.getProductId());
        productItem.setProduct(product);
        productItemRepository.save(productItem);

        return Mapper.productItemToProductItemResponseDto(productItem);
    }

    @Override
    public List<ProductItemResponseDto> getProductItems() {
        List<ProductItem> productItems = new ArrayList<>(productItemRepository.findAll());
        return Mapper.productItemsToProductItemsResponseDtos(productItems);
    }

    @Override
    public ProductItemResponseDto getProductItemById(Long productItemId) {
        return Mapper.productItemToProductItemResponseDto(getProductItem(productItemId));
    }

    @Override
    public ProductItem getProductItem(Long productItemId) {
        return productItemRepository.findById(productItemId)
                .orElseThrow(() -> new EntityNotFoundException(productItemId, ProductItem.class));
    }

    @Override
    @Transactional
    public void deleteProductItem(Long productItemId) {
        ProductItem productItem = getProductItem(productItemId);
        productItemRepository.delete(productItem);
    }

    @Override
    public ProductItemResponseDto editProductItem(Long productItemId, ProductItemRequestDto productItemRequestDto) {
        ProductItem productItemToEdit = getProductItem(productItemId);
        productItemToEdit.setSKU(productItemRequestDto.getSku());
        productItemToEdit.setQty_in_stock(productItemRequestDto.getQuantityInStock());
        productItemToEdit.setProduct_image(productItemRequestDto.getProductImage());
        productItemToEdit.setPrice(productItemRequestDto.getPrice());

        if(productItemRequestDto.getProductId() == null){
            throw new IllegalArgumentException("Product has to have a category");
        }
        Product product = productService.getProduct(productItemRequestDto.getProductId());
        productItemToEdit.setProduct(product);

        return Mapper.productItemToProductItemResponseDto(productItemToEdit);
    }

    @Override
    public ProductItemResponseDto addVariationOptionToProductItem(Long product_item_id, Long variation_option_id) {
        ProductItem productItem = getProductItem(product_item_id);

        VariationOption variationOption = variationOptionService.getVariationOption(variation_option_id);
        productItem.getVariationOptions().add(variationOption);
        productItemRepository.save(productItem);

        return Mapper.productItemToProductItemResponseDto(productItem);
    }

    @Override
    public ProductItemResponseDto removeVariationOptionFromProductItem(Long product_item_id, Long variation_option_id) {
        ProductItem productItem = getProductItem(product_item_id);

        VariationOption variationOption = variationOptionService.getVariationOption(variation_option_id);
        productItem.getVariationOptions().remove(variationOption);
        productItemRepository.save(productItem);

        return Mapper.productItemToProductItemResponseDto(productItem);
    }
}

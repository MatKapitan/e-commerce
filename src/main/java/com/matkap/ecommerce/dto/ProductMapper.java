package com.matkap.ecommerce.dto;

import com.matkap.ecommerce.dto.responseDto.product.ProductResponseDto;
import com.matkap.ecommerce.model.product.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements IMapper<Product, ProductResponseDto>{


    @Override
    public ProductResponseDto toDto(Product entity) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(entity.getId());
        productResponseDto.setName(entity.getName());
        productResponseDto.setDescription(entity.getDescription());
        productResponseDto.setProductImage(entity.getProductImage());
        productResponseDto.setCategoryName(entity.getProductCategory().getCategoryName());
        return productResponseDto;
    }

    @Override
    public Product toEntity(ProductResponseDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setProductImage(dto.getProductImage());
        return product;
    }
}

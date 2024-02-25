package com.matkap.ecommerce.service.product;

import com.matkap.ecommerce.dto.requestDto.product.ProductRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductResponseDto;
import com.matkap.ecommerce.model.product.Product;

import java.util.List;

public interface ProductService {


    public ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    public List<ProductResponseDto> getProducts();
    public ProductResponseDto getProductById(Long productId);
    public Product getProduct(Long productId);
    public ProductResponseDto deleteProduct(Long productId);
    public ProductResponseDto editProduct(Long productId, ProductRequestDto productRequestDto);



}

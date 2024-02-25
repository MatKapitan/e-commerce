package com.matkap.ecommerce.service.product.impl;

import com.matkap.ecommerce.dto.Mapper;
import com.matkap.ecommerce.dto.requestDto.product.ProductRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductResponseDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.product.Product;
import com.matkap.ecommerce.repository.product.ProductRepository;
import com.matkap.ecommerce.service.product.ProductCategoryService;
import com.matkap.ecommerce.service.product.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

   private final ProductRepository productRepository;

   private final ProductCategoryService productCategoryService;

    public ProductServiceImpl(ProductRepository productItemRepository, ProductCategoryService productCategoryService) {
        this.productRepository = productItemRepository;
        this.productCategoryService = productCategoryService;
    }


    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setProduct_image(productRequestDto.getProduct_image());
        if (productRequestDto.getProduct_category_id() == null){
            throw new IllegalArgumentException("Product has to have a category");
        }
        product.setProductCategory(productCategoryService.getProductCategory(productRequestDto.getProduct_category_id()));
        productRepository.save(product);

        return Mapper.productToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getProducts() {
        List<Product> allProducts = productRepository.findAll();
        return Mapper.productsToProductsResponseDtos(allProducts);
    }

    @Override
    public ProductResponseDto getProductById(Long productId) {
        return Mapper.productToProductResponseDto(getProduct(productId));
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(()->
                new EntityNotFoundException(productId, Product.class));
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = getProduct(productId);
        productRepository.delete(product);
    }
    @Transactional
    @Override
    public ProductResponseDto editProduct(Long productId, ProductRequestDto productRequestDto) {

        Product productToEdit = getProduct(productId);
        productToEdit.setName(productRequestDto.getName());
        productToEdit.setDescription(productRequestDto.getDescription());
        productToEdit.setProduct_image(productRequestDto.getProduct_image());
        if (productRequestDto.getProduct_category_id() == null){
            throw new IllegalArgumentException("Product has to have a category");
        }
        productToEdit.setProductCategory(productCategoryService.getProductCategory(productRequestDto.getProduct_category_id()));

        return Mapper.productToProductResponseDto(productToEdit);
    }
}

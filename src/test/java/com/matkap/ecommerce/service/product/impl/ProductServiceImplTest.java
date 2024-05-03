package com.matkap.ecommerce.service.product.impl;

import com.matkap.ecommerce.dto.requestDto.product.ProductRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductResponseDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.product.Product;
import com.matkap.ecommerce.model.product.ProductCategory;
import com.matkap.ecommerce.repository.product.ProductRepository;
import com.matkap.ecommerce.service.product.ProductCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {


    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductCategoryService productCategoryService;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void should_successfully_save_a_product(){
        ProductCategory productCategory = new ProductCategory(null, null,"clothing");
        ProductRequestDto productRequestDto = new ProductRequestDto("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg",1L);
        Product product = new Product("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg", productCategory);
        when(productCategoryService.getProductCategory(anyLong())).thenReturn(productCategory);
        when(productRepository.save(product)).thenReturn(product);

        ProductResponseDto result = productService.createProduct(productRequestDto);

        assertEquals(productRequestDto.getName(), result.getName());
        assertEquals(productRequestDto.getDescription(), result.getDescription());
        assertEquals(productRequestDto.getProductImage(), result.getProductImage());
    }

    @Test
    public void should_throw_illegal_argument_exception_then_product_category_is_null(){
        ProductRequestDto productRequestDto = new ProductRequestDto("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg",null);
        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(productRequestDto));
    }

    @Test
    public void should_return_list_of_products(){
        ProductCategory productCategory = new ProductCategory(null, null,"clothing");
        List<Product> productList = Arrays.asList(new Product("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg", productCategory),
                new Product("hoodies", "sweatshirt or a jacket with a hood", "hoodie.jpg", productCategory));
        when(productRepository.findAll()).thenReturn(productList);

        List<ProductResponseDto> result = productService.getProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(productList.get(0).getName(), result.get(0).getName());
        assertEquals(productList.get(0).getDescription(), result.get(0).getDescription());
        assertEquals(productList.get(0).getProductImage(), result.get(0).getProductImage());
        //
        assertEquals(productList.get(1).getName(), result.get(1).getName());
        assertEquals(productList.get(1).getDescription(), result.get(1).getDescription());
        assertEquals(productList.get(1).getProductImage(), result.get(1).getProductImage());
    }

    @Test
    public  void should_return_product_by_id(){
        ProductCategory productCategory = new ProductCategory(null, null,"clothing");
        Product product = new Product("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg", productCategory);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        ProductResponseDto result = productService.getProductById(anyLong());

        assertEquals(product.getName(), result.getName());
        assertEquals(product.getDescription(), result.getDescription());
        assertEquals(product.getProductImage(), result.getProductImage());
    }

    @Test
    public void should_throw_entity_not_found_exception_if_product_does_not_exist(){
        EntityNotFoundException result = assertThrows(EntityNotFoundException.class, () -> productService.getProductById(anyLong()));

        assertEquals(result.getMessage(), "The product with id '0' does not exist in our records" );
    }

    @Test
    public void  should_delete_product(){
        ProductCategory productCategory = new ProductCategory(null, null,"clothing");
        Product product = new Product("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg", productCategory);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        productService.deleteProduct(anyLong());
        verify(productRepository, times(1)).delete(product);
    }

    @Test

    public void should_successfully_edit_a_product(){
        ProductCategory productCategory = new ProductCategory(null, null,"clothing");
        ProductRequestDto productRequestDto = new ProductRequestDto("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg",1L);
        Product product = new Product("hoodies", "sweatshirt or a jacket with a hood", "hoodie.jpg", productCategory);
        when(productCategoryService.getProductCategory(anyLong())).thenReturn(productCategory);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        ProductResponseDto result = productService.editProduct(anyLong(), productRequestDto);

        assertEquals(productRequestDto.getName(), result.getName());
        assertEquals(productRequestDto.getDescription(), result.getDescription());
        assertEquals(productRequestDto.getProductImage(), result.getProductImage());
    }








}
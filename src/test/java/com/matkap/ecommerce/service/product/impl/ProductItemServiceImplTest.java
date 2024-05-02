package com.matkap.ecommerce.service.product.impl;

import com.matkap.ecommerce.dto.requestDto.product.ProductItemRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductItemResponseDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.product.Product;
import com.matkap.ecommerce.model.product.ProductItem;
import com.matkap.ecommerce.model.product.Variation;
import com.matkap.ecommerce.model.product.VariationOption;
import com.matkap.ecommerce.repository.product.ProductItemRepository;
import com.matkap.ecommerce.service.product.ProductService;
import com.matkap.ecommerce.service.product.VariationOptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


class ProductItemServiceImplTest {


    @Mock
    private ProductService productService;
    @Mock
    private ProductItemRepository productItemRepository;
    @Mock
    private VariationOptionService variationOptionService;
    @InjectMocks
    private ProductItemServiceImpl productItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_product_item(){
        ProductItemRequestDto productItemRequestDto = new ProductItemRequestDto("t-sh-red-S", 10L, "red-t-shirt.jpg", BigDecimal.TEN, 1L );
        Product product = new Product("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg",null);
        when(productService.getProduct(1L)).thenReturn(product);
        ProductItem productItem = new ProductItem("t-sh-red-S", 10L, "red-t-shirt.jpg", BigDecimal.TEN, product);
        when(productItemRepository.save(productItem)).thenReturn(productItem);

        ProductItemResponseDto result = productItemService.createProductItem(productItemRequestDto);

        assertEquals(productItemRequestDto.getSku(), result.getSKU());
        assertEquals(productItemRequestDto.getProductImage(), result.getProductImage());
        assertEquals(productItemRequestDto.getQuantityInStock(), result.getQtyInStock());
        assertEquals(productItemRequestDto.getPrice(), result.getPrice());
    }

    @Test
    public void should_throw_illegal_argument_exception_when_product_is_null(){

        ProductItemRequestDto productItemRequestDto = new ProductItemRequestDto("t-sh-red-S", 10L, "red-t-shirt.jpg", BigDecimal.TEN, null );
        assertThrows(IllegalArgumentException.class, () -> productItemService.createProductItem(productItemRequestDto));

    }

    @Test
    public void should_return_list_of_product_items(){
       Product product = new Product("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg",null);
       List<ProductItem> productItemList = Arrays.asList( new ProductItem("t-sh-red-S", 10L, "red-t-shirt.jpg", BigDecimal.TEN, product),
                new ProductItem("t-sh-blu-M", 9L, "blue-t-shirt.jpg", new BigDecimal("15.50"), product));
        when(productItemRepository.findAll()).thenReturn(productItemList);

        List<ProductItemResponseDto> result = productItemService.getProductItems();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(productItemList.get(0).getProductImage(), result.get(0).getProductImage());
        assertEquals(productItemList.get(0).getSku(), result.get(0).getSKU());
        assertEquals(productItemList.get(0).getPrice(), result.get(0).getPrice());
        assertEquals(productItemList.get(0).getQtyInStock(), result.get(0).getQtyInStock());
        //
        assertEquals(productItemList.get(1).getProductImage(), result.get(1).getProductImage());
        assertEquals(productItemList.get(1).getSku(), result.get(1).getSKU());
        assertEquals(productItemList.get(1).getPrice(), result.get(1).getPrice());
        assertEquals(productItemList.get(1).getQtyInStock(), result.get(1).getQtyInStock());
    }

    @Test
    public void should_return_product_item_by_id(){
        Product product = new Product("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg",null);
        ProductItem productItem = new ProductItem("t-sh-blu-M", 9L, "blue-t-shirt.jpg", new BigDecimal("15.50"), product);
        when(productItemRepository.findById(anyLong())).thenReturn(Optional.of(productItem));

        ProductItemResponseDto result = productItemService.getProductItemById(anyLong());

        assertEquals(productItem.getQtyInStock(), result.getQtyInStock());
    }

    @Test
    public void should_throw_entity_not_found_exception_if_product_item_does_not_exist(){
        EntityNotFoundException result = assertThrows(EntityNotFoundException.class, () -> productItemService.getProductItemById(anyLong()));

        assertEquals(result.getMessage(), "The productitem with id '0' does not exist in our records" );
    }

    @Test
    public void should_delete_product_item(){
        Product product = new Product("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg",null);
        ProductItem productItem = new ProductItem("t-sh-blu-M", 9L, "blue-t-shirt.jpg", new BigDecimal("15.50"), product);
        when(productItemRepository.findById(anyLong())).thenReturn(Optional.of(productItem));

        productItemService.deleteProductItem(anyLong());
        verify(productItemRepository, times(1)).delete(productItem);
    }

    @Test
    public void should_successfully_edit_a_product_item(){
        Product product = new Product("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg",null);
        ProductItem productItem = new ProductItem("t-sh-blu-M", 9L, "blue-t-shirt.jpg", new BigDecimal("15.50"), product);
        ProductItemRequestDto productItemRequestDto = new ProductItemRequestDto("t-sh-red-S", 10L, "red-t-shirt.jpg", BigDecimal.TEN, 1L );
        when(productService.getProduct(1L)).thenReturn(product);
        when(productItemRepository.findById(anyLong())).thenReturn(Optional.of(productItem));

        ProductItemResponseDto result = productItemService.editProductItem(anyLong(), productItemRequestDto);

        assertEquals(productItemRequestDto.getSku(), result.getSKU());
        assertEquals(productItemRequestDto.getProductImage(), result.getProductImage());
        assertEquals(productItemRequestDto.getQuantityInStock(), result.getQtyInStock());
        assertEquals(productItemRequestDto.getPrice(), result.getPrice());
    }

    @Test
    public void should_add_variation_option_to_product_item(){
        Variation variation = new Variation(null, "collor");
        VariationOption variationOption = new VariationOption(variation, "red");
        Product product = new Product("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg",null);
        ProductItem productItem = new ProductItem("t-sh-blu-M", 9L, "blue-t-shirt.jpg", new BigDecimal("15.50"), product);
        when(variationOptionService.getVariationOption(anyLong())).thenReturn(variationOption);
        when(productItemRepository.findById(anyLong())).thenReturn(Optional.of(productItem));

        ProductItemResponseDto result = productItemService.addVariationOptionToProductItem(0L, 0L);

        assertEquals(variationOption.getValue(), result.getVariationOptionValues().get(0));
    }

    @Test
    public void should_remove_variation_option_from_product_item(){
        Variation variation = new Variation(null, "collor");
        VariationOption variationOption = new VariationOption(variation, "red");
        Product product = new Product("t-shirts", "fabric shirt named after the T shape", "t-shirt.jpg",null);
        ProductItem productItem = new ProductItem("t-sh-blu-M", 9L, "blue-t-shirt.jpg", new BigDecimal("15.50"), product);
        productItem.getVariationOptions().add(variationOption);
        when(variationOptionService.getVariationOption(anyLong())).thenReturn(variationOption);
        when(productItemRepository.findById(anyLong())).thenReturn(Optional.of(productItem));

        ProductItemResponseDto result = productItemService.removeVariationOptionFromProductItem(0L, 0L);

        assertNotNull(result);
        assertEquals(0, result.getVariationOptionValues().size());
    }
}
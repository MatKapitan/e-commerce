package com.matkap.ecommerce.controller.product;


import com.matkap.ecommerce.dto.requestDto.product.ProductItemRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductItemResponseDto;
import com.matkap.ecommerce.service.product.ProductItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productitem")
public class ProductItemController {

    private final ProductItemService productItemService;


    public ProductItemController(ProductItemService productItemService) {
        this.productItemService = productItemService;
    }


    @PostMapping("/add")
    public ResponseEntity<ProductItemResponseDto> addProductItem(@RequestBody ProductItemRequestDto productItemRequestDto){
        ProductItemResponseDto productItemResponseDto = productItemService.createProductItem(productItemRequestDto);
        return new ResponseEntity<>(productItemResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ProductItemResponseDto> getProductItemById(@PathVariable Long id){
        ProductItemResponseDto productItemResponseDto = productItemService.getProductItemById(id);
        return new ResponseEntity<>(productItemResponseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductItemResponseDto>> getAllProductItems(){
        List<ProductItemResponseDto> productItems = productItemService.getProductItems();
        return new ResponseEntity<>(productItems, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Void> deleteProductItem(@PathVariable Long id){
        productItemService.deleteProductItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductItemResponseDto> editProductItem(@PathVariable Long id,
                                                                  @RequestBody ProductItemRequestDto productItemRequestDto){
        ProductItemResponseDto productItemResponseDto = productItemService.editProductItem(id, productItemRequestDto);
        return new ResponseEntity<>(productItemResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{product_item_id}/variation-option/{variation_option_id}")
    public ResponseEntity<ProductItemResponseDto> addVariationOptionToProductItem(@PathVariable Long product_item_id,
                                                                       @PathVariable Long variation_option_id){
        ProductItemResponseDto productItemResponseDto = productItemService.addVariationOptionToProductItem(product_item_id, variation_option_id);
        return new ResponseEntity<>(productItemResponseDto, HttpStatus.OK);
    }

    @PutMapping("/remove/{product_item_id}/variation-option/{variation_option_id}")
    public ResponseEntity<ProductItemResponseDto> deleteVariationOptionToProductItem(@PathVariable Long product_item_id,
                                                                                  @PathVariable Long variation_option_id){
        ProductItemResponseDto productItemResponseDto = productItemService.removeVariationOptionFromProductItem(product_item_id, variation_option_id);
        return new ResponseEntity<>(productItemResponseDto, HttpStatus.OK);
    }

}

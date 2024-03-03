package com.matkap.ecommerce.controller.product;


import com.matkap.ecommerce.dto.requestDto.product.ProductItemRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductItemResponseDto;
import com.matkap.ecommerce.service.product.ProductItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-item")
public class ProductItemController {

    private final ProductItemService productItemService;


    public ProductItemController(ProductItemService productItemService) {
        this.productItemService = productItemService;
    }


    @PostMapping("/create")
    public ResponseEntity<ProductItemResponseDto> addProductItem(@Valid @RequestBody ProductItemRequestDto productItemRequestDto){
        ProductItemResponseDto productItemResponseDto = productItemService.createProductItem(productItemRequestDto);
        return new ResponseEntity<>(productItemResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{productItemId}")
    public  ResponseEntity<ProductItemResponseDto> getProductItemById(@PathVariable Long productItemId){
        ProductItemResponseDto productItemResponseDto = productItemService.getProductItemById(productItemId);
        return new ResponseEntity<>(productItemResponseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductItemResponseDto>> getAllProductItems(){
        List<ProductItemResponseDto> productItems = productItemService.getProductItems();
        return new ResponseEntity<>(productItems, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productItemId}")
    public  ResponseEntity<Void> deleteProductItem(@PathVariable Long productItemId){
        productItemService.deleteProductItem(productItemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{productItemId}")
    public ResponseEntity<ProductItemResponseDto> editProductItem(@PathVariable Long productItemId,
                                                                  @Valid @RequestBody ProductItemRequestDto productItemRequestDto){
        ProductItemResponseDto productItemResponseDto = productItemService.editProductItem(productItemId, productItemRequestDto);
        return new ResponseEntity<>(productItemResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{productItemId}/variation-option/{variationOptionId}")
    public ResponseEntity<ProductItemResponseDto> addVariationOptionToProductItem(@PathVariable Long productItemId,
                                                                       @PathVariable Long variationOptionId){
        ProductItemResponseDto productItemResponseDto = productItemService.addVariationOptionToProductItem(productItemId, variationOptionId);
        return new ResponseEntity<>(productItemResponseDto, HttpStatus.OK);
    }

    @PutMapping("/remove/{productItemId}/variation-option/{variationOptionId}")
    public ResponseEntity<ProductItemResponseDto> deleteVariationOptionToProductItem(@PathVariable Long productItemId,
                                                                                  @PathVariable Long variationOptionId){
        ProductItemResponseDto productItemResponseDto = productItemService.removeVariationOptionFromProductItem(productItemId, variationOptionId);
        return new ResponseEntity<>(productItemResponseDto, HttpStatus.OK);
    }

}

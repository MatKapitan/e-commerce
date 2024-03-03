package com.matkap.ecommerce.controller.product;


import com.matkap.ecommerce.dto.requestDto.product.ProductCategoryRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductCategoryResponseDto;
import com.matkap.ecommerce.service.product.ProductCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-category")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductCategoryResponseDto> addProductCategory(@Valid @RequestBody ProductCategoryRequestDto productCategoryRequestDto){
        ProductCategoryResponseDto productCategoryResponseDto = productCategoryService.createProductCategory(productCategoryRequestDto);
        return new ResponseEntity<>(productCategoryResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{productCategoryId}")
    public  ResponseEntity<ProductCategoryResponseDto> getProductCategoryById(@PathVariable Long productCategoryId){
        ProductCategoryResponseDto productCategoryById = productCategoryService.getProductCategoryById(productCategoryId);
        return new ResponseEntity<>(productCategoryById, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductCategoryResponseDto>> getAllProductCategories(){
        List<ProductCategoryResponseDto> productCategoryResponseDtos = productCategoryService.getProductCategories();
        return new ResponseEntity<>(productCategoryResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productCategoryId}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Long productCategoryId){
        productCategoryService.deleteProductCategory(productCategoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{productCategoryId}")
    public ResponseEntity<ProductCategoryResponseDto> editProductCategory(@PathVariable Long productCategoryId,
                                                                          @Valid @RequestBody ProductCategoryRequestDto productCategoryRequestDto){
        ProductCategoryResponseDto productCategoryResponseDto = productCategoryService.editProductCategory(productCategoryId, productCategoryRequestDto);
        return new ResponseEntity<>(productCategoryResponseDto, HttpStatus.OK);
    }

}

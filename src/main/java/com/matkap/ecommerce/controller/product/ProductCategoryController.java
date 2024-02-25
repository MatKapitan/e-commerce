package com.matkap.ecommerce.controller.product;


import com.matkap.ecommerce.dto.requestDto.product.ProductCategoryRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductCategoryResponseDto;
import com.matkap.ecommerce.service.product.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productcategory")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<ProductCategoryResponseDto> addProductCategory(@RequestBody ProductCategoryRequestDto productCategoryRequestDto){
        ProductCategoryResponseDto productCategoryResponseDto = productCategoryService.createProductCategory(productCategoryRequestDto);
        return new ResponseEntity<>(productCategoryResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ProductCategoryResponseDto> getProductCategoryById(@PathVariable Long id){
        ProductCategoryResponseDto productCategoryById = productCategoryService.getProductCategoryById(id);
        return new ResponseEntity<>(productCategoryById, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductCategoryResponseDto>> getAllProductCategories(){
        List<ProductCategoryResponseDto> productCategoryResponseDtos = productCategoryService.getProductCategories();
        return new ResponseEntity<>(productCategoryResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductCategoryResponseDto> deleteProductCategory(@PathVariable Long id){
        ProductCategoryResponseDto productCategoryResponseDto = productCategoryService.deleteProductCategory(id);
        return new ResponseEntity<>(productCategoryResponseDto, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductCategoryResponseDto> editProductCategory(@PathVariable Long product_category_id,
                                                                          @RequestBody ProductCategoryRequestDto productCategoryRequestDto){
        ProductCategoryResponseDto productCategoryResponseDto = productCategoryService.editProductCategory(product_category_id, productCategoryRequestDto);
        return new ResponseEntity<>(productCategoryResponseDto, HttpStatus.OK);
    }

}

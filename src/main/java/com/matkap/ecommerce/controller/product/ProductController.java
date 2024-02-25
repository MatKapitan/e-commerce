package com.matkap.ecommerce.controller.product;

import com.matkap.ecommerce.dto.requestDto.product.ProductRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductResponseDto;
import com.matkap.ecommerce.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService ;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto productResponseDto = productService.createProduct(productRequestDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id){
        ProductResponseDto productResponseDto = productService.getProductById(id);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        List<ProductResponseDto> productResponseDtos = productService.getProducts();
        return  new ResponseEntity<>(productResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable Long id){
        ProductResponseDto productResponseDto = productService.deleteProduct(id);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductResponseDto> editProduct(@PathVariable Long id,
                                                          @RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto productResponseDto = productService.editProduct(id, productRequestDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }


}

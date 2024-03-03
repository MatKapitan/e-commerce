package com.matkap.ecommerce.controller.product;

import com.matkap.ecommerce.dto.requestDto.product.ProductRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductResponseDto;
import com.matkap.ecommerce.service.product.ProductService;
import jakarta.validation.Valid;
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



    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto> addProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto productResponseDto = productService.createProduct(productRequestDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long productId){
        ProductResponseDto productResponseDto = productService.getProductById(productId);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        List<ProductResponseDto> productResponseDtos = productService.getProducts();
        return  new ResponseEntity<>(productResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{productId}")
    public ResponseEntity<ProductResponseDto> editProduct(@PathVariable Long productId,
                                                          @Valid @RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto productResponseDto = productService.editProduct(productId, productRequestDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }


}

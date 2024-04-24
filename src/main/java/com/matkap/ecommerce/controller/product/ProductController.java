package com.matkap.ecommerce.controller.product;

import com.matkap.ecommerce.dto.requestDto.product.ProductRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductResponseDto;
import com.matkap.ecommerce.exception.ErrorResponse;
import com.matkap.ecommerce.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Product Controller", description = "Create, retrieve, delete and edit product " )
public class ProductController {

    private final ProductService productService ;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of product"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Create Product", description = "Creates a product from the provided payload")
    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto> addProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto productResponseDto = productService.createProduct(productRequestDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of product", content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
    })
    @Operation(summary = "Retrieve Product", description = "Returns a product based on id" )
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long productId){
        ProductResponseDto productResponseDto = productService.getProductById(productId);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }
    @ApiResponse(responseCode = "200", description = "Successful retrieval of products", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductResponseDto.class))))
    @Operation(summary = "Retrieve Products", description = "Provides a list of all products")
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        List<ProductResponseDto> productResponseDtos = productService.getProducts();
        return  new ResponseEntity<>(productResponseDtos, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful deletion of product")
    })
    @Operation(summary = "Deletes Product", description = "Deletes a product based on id" )
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful edit of product", content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
    })
    @Operation(summary = "Edit Product", description = "Edits a product from the provided payload and id")
    @PutMapping("/edit/{productId}")
    public ResponseEntity<ProductResponseDto> editProduct(@PathVariable Long productId,
                                                          @Valid @RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto productResponseDto = productService.editProduct(productId, productRequestDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }


}

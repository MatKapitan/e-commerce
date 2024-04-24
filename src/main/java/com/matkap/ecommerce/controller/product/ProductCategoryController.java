package com.matkap.ecommerce.controller.product;


import com.matkap.ecommerce.dto.requestDto.product.ProductCategoryRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductCategoryResponseDto;
import com.matkap.ecommerce.exception.ErrorResponse;
import com.matkap.ecommerce.service.product.ProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product-category", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Product category Controller", description = "Create, retrieve, delete and edit product categories" )
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of product category"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Create Product category", description = "Creates a product category from the provided payload")
    @PostMapping("/create")
    public ResponseEntity<ProductCategoryResponseDto> addProductCategory(@Valid @RequestBody ProductCategoryRequestDto productCategoryRequestDto){
        ProductCategoryResponseDto productCategoryResponseDto = productCategoryService.createProductCategory(productCategoryRequestDto);
        return new ResponseEntity<>(productCategoryResponseDto, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product category doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of product category", content = @Content(schema = @Schema(implementation = ProductCategoryResponseDto.class))),
    })
    @Operation(summary = "Retrieve order line", description = "Returns a order line based on id")
    @GetMapping("/{productCategoryId}")
    public  ResponseEntity<ProductCategoryResponseDto> getProductCategoryById(@PathVariable Long productCategoryId){
        ProductCategoryResponseDto productCategoryById = productCategoryService.getProductCategoryById(productCategoryId);
        return new ResponseEntity<>(productCategoryById, HttpStatus.OK);
    }
    @ApiResponse(responseCode = "200", description = "Successful retrieval of product categories", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductCategoryResponseDto.class))))
    @Operation(summary = "Retrieve order lines", description = "Provides a list of all product categories" )
    @GetMapping("/all")
    public ResponseEntity<List<ProductCategoryResponseDto>> getAllProductCategories(){
        List<ProductCategoryResponseDto> productCategoryResponseDtos = productCategoryService.getProductCategories();
        return new ResponseEntity<>(productCategoryResponseDtos, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product category doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful deletion of product category")
    })
    @Operation(summary = "Deletes Product category", description = "Deletes a product category based on id" )
    @DeleteMapping("/delete/{productCategoryId}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Long productCategoryId){
        productCategoryService.deleteProductCategory(productCategoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product category doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful edit of product category", content = @Content(schema = @Schema(implementation = ProductCategoryResponseDto.class))),
    })
    @Operation(summary = "Edit Product category", description = "Edits a product category from the provided payload and id")
    @PutMapping("/edit/{productCategoryId}")
    public ResponseEntity<ProductCategoryResponseDto> editProductCategory(@PathVariable Long productCategoryId,
                                                                          @Valid @RequestBody ProductCategoryRequestDto productCategoryRequestDto){
        ProductCategoryResponseDto productCategoryResponseDto = productCategoryService.editProductCategory(productCategoryId, productCategoryRequestDto);
        return new ResponseEntity<>(productCategoryResponseDto, HttpStatus.OK);
    }

}

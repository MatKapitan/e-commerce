package com.matkap.ecommerce.controller.product;


import com.matkap.ecommerce.dto.requestDto.product.ProductItemRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.ProductItemResponseDto;
import com.matkap.ecommerce.exception.ErrorResponse;
import com.matkap.ecommerce.service.product.ProductItemService;
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
@RequestMapping(value = "/product-item", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Product item Controller", description = "Create, retrieve, delete and edit product item" )
public class ProductItemController {

    private final ProductItemService productItemService;


    public ProductItemController(ProductItemService productItemService) {
        this.productItemService = productItemService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of product item"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Create Product item", description = "Creates a product item from the provided payload")
    @PostMapping("/create")
    public ResponseEntity<ProductItemResponseDto> addProductItem(@Valid @RequestBody ProductItemRequestDto productItemRequestDto){
        ProductItemResponseDto productItemResponseDto = productItemService.createProductItem(productItemRequestDto);
        return new ResponseEntity<>(productItemResponseDto, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product item doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of product item", content = @Content(schema = @Schema(implementation = ProductItemResponseDto.class))),
    })
    @Operation(summary = "Retrieve product item", description = "Returns a product item based on id" )
    @GetMapping("/{productItemId}")
    public  ResponseEntity<ProductItemResponseDto> getProductItemById(@PathVariable Long productItemId){
        ProductItemResponseDto productItemResponseDto = productItemService.getProductItemById(productItemId);
        return new ResponseEntity<>(productItemResponseDto, HttpStatus.OK);
    }
    @ApiResponse(responseCode = "200", description = "Successful retrieval of product items", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductItemResponseDto.class))))
    @Operation(summary = "Retrieve product items", description = "Provides a list of all product items" )
    @GetMapping("/all")
    public ResponseEntity<List<ProductItemResponseDto>> getAllProductItems(){
        List<ProductItemResponseDto> productItems = productItemService.getProductItems();
        return new ResponseEntity<>(productItems, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product item doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful deletion of product item")
    })
    @Operation(summary = "Deletes Product item", description = "Deletes a product item based on id" )
    @DeleteMapping("/delete/{productItemId}")
    public  ResponseEntity<Void> deleteProductItem(@PathVariable Long productItemId){
        productItemService.deleteProductItem(productItemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product item doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful edit of product item", content = @Content(schema = @Schema(implementation = ProductItemResponseDto.class))),
    })
    @Operation(summary = "Edit Product item", description = "Edits a product item from the provided payload and id")
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

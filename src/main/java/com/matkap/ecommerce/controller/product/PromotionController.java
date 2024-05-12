package com.matkap.ecommerce.controller.product;


import com.matkap.ecommerce.dto.requestDto.product.PromotionRequestDto;
import com.matkap.ecommerce.exception.ErrorResponse;
import com.matkap.ecommerce.model.product.Promotion;
import com.matkap.ecommerce.service.product.PromotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/promotions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Promotion Controller", description = "Create, retrieve, delete and edit promotions" )
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of promotion"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Create Shop order", description = "Creates a promotion from the provided payload")
    @PostMapping("/create")
    public ResponseEntity<PromotionRequestDto> createPromotion(@Valid @RequestBody PromotionRequestDto promotionRequestDto){
        PromotionRequestDto promotion = promotionService.createPromotion(promotionRequestDto);
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Promotion doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of promotion", content = @Content(schema = @Schema(implementation = Promotion.class))),
    })
    @Operation(summary = "Retrieve Promotion", description = "Returns a promotion based on id" )
    @GetMapping("/{promotionId}")
    public ResponseEntity<PromotionRequestDto> getPromotionById(@PathVariable Long promotionId){
        PromotionRequestDto promotion = promotionService.getPromotionById(promotionId);
        return new ResponseEntity<>(promotion, HttpStatus.OK);

    }
    @Operation(summary = "Retrieve promotions", description = "Provides a list of all promotions" )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of all promotions", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Promotion.class))))
    @GetMapping("/all")
    public ResponseEntity<List<PromotionRequestDto>> getAllPromotions(){
        List<PromotionRequestDto> promotions = promotionService.getAllPromotions();
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Promotion doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful deletion of promotion")
    })
    @Operation(summary = "Deletes Promotion", description = "Deletes a promotion based on id" )
    @DeleteMapping("/delete/{promotionId}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long promotionId){
        promotionService.deletePromotion(promotionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Promotion doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful edit of promotion", content = @Content(schema = @Schema(implementation = PromotionRequestDto.class))),
    })
    @Operation(summary = "Edit Promotion", description = "Edits a promotion from the provided payload and id")
    @PutMapping("/edit/{promotionId}")
    public ResponseEntity<PromotionRequestDto> updatePromotion(@PathVariable Long promotionId,
                                                     @Valid @RequestBody PromotionRequestDto promotionRequestDto){
        PromotionRequestDto promotion = promotionService.updatePromotion(promotionRequestDto, promotionId);
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }

    @PutMapping("/{promotionId}/add/product-category/{productCategoryId}")
    public ResponseEntity<PromotionRequestDto> addProductCategory(@PathVariable Long promotionId,
                                                        @PathVariable Long productCategoryId){
        PromotionRequestDto promotion = promotionService.addProductCategoryToPromotion(promotionId, productCategoryId);
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }

    @PutMapping("/{promotionId}/remove/product-category/{productCategoryId}")
    public ResponseEntity<PromotionRequestDto> removeProductCategory(@PathVariable Long promotionId,
                                                            @PathVariable Long productCategoryId){
        PromotionRequestDto promotion = promotionService.removeProductCategoryFromPromotion(promotionId, productCategoryId);
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }







}

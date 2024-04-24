package com.matkap.ecommerce.controller.product;

import com.matkap.ecommerce.dto.requestDto.product.VariationOptionRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.VariationOptionResponseDto;
import com.matkap.ecommerce.exception.ErrorResponse;
import com.matkap.ecommerce.model.order.ShopOrder;
import com.matkap.ecommerce.service.product.VariationOptionService;
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
@RequestMapping(value = "/variation-option", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Variation option Controller", description = "Create, retrieve, delete and edit variation options" )
public class VariationOptionController {

private final VariationOptionService variationOptionService;

    public VariationOptionController(VariationOptionService variationOptionService) {
        this.variationOptionService = variationOptionService;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of variation option"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Create Variation option", description = "Creates a variation option from the provided payload")
    @PostMapping("/create")
    public ResponseEntity<VariationOptionResponseDto> addVariationOption(@Valid @RequestBody VariationOptionRequestDto variationOptionRequestDto){
        VariationOptionResponseDto variationOptionResponseDto = variationOptionService.createVariationOption(variationOptionRequestDto);
        return new ResponseEntity<>(variationOptionResponseDto, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Variation option doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of variation option", content = @Content(schema = @Schema(implementation = VariationOptionResponseDto.class))),
    })
    @Operation(summary = "Retrieve Variation option", description = "Returns a variation option based on id" )
    @GetMapping("/{variationOptionId}")
    public ResponseEntity<VariationOptionResponseDto> getVariationOptionById(@PathVariable Long variationOptionId){
        VariationOptionResponseDto variationOptionResponseDto = variationOptionService.getVariationOptionById(variationOptionId);
        return new ResponseEntity<>(variationOptionResponseDto, HttpStatus.OK);
    }
    @ApiResponse(responseCode = "200", description = "Successful retrieval of variation options", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ShopOrder.class))))
    @Operation(summary = "Retrieve order lines", description = "Provides a list of all variation options" )
    @GetMapping("/all")
    public ResponseEntity<List<VariationOptionResponseDto>> getAllVariationOptions(){
        List<VariationOptionResponseDto> variationOptionsResponseDto = variationOptionService.getVariationOptions();
        return new ResponseEntity<>(variationOptionsResponseDto, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Variation option line doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful deletion of variation option")
    })
    @Operation(summary = "Deletes Variation option", description = "Deletes a variation option based on id" )
    @DeleteMapping("/delete/{variationOptionId}")
    public ResponseEntity<VariationOptionResponseDto> deleteVariationOption(@PathVariable Long variationOptionId){
        variationOptionService.deleteVariationOption(variationOptionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Variation option doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful edit of variation option", content = @Content(schema = @Schema(implementation = VariationOptionResponseDto.class))),
    })
    @Operation(summary = "Edit Variation option", description = "Edits a variation option from the provided payload and id")
    @PutMapping("/edit/{variationOptionId}")
    public ResponseEntity<VariationOptionResponseDto> editVariationOption(@PathVariable Long variationOptionId,
                                                                          @Valid @RequestBody VariationOptionRequestDto variationOptionRequestDto){
        VariationOptionResponseDto variationOptionResponseDto = variationOptionService.editVariationOption(variationOptionId, variationOptionRequestDto);
        return new ResponseEntity<>(variationOptionResponseDto, HttpStatus.OK);
    }
}

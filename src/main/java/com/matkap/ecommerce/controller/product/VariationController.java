package com.matkap.ecommerce.controller.product;

import com.matkap.ecommerce.dto.requestDto.product.VariationRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.VariationResponseDto;
import com.matkap.ecommerce.exception.ErrorResponse;
import com.matkap.ecommerce.service.product.VariationService;
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
@RequestMapping(value = "/variation", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Variation Controller", description = "Create, retrieve, delete and edit variation" )
public class VariationController {


    private final VariationService variationService;

    public VariationController(VariationService variationService) {
        this.variationService = variationService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of Variation"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Create Variation", description = "Creates a variation from the provided payload")
    @PostMapping("/create")
    public ResponseEntity<VariationResponseDto> addVariation(@Valid @RequestBody VariationRequestDto variationRequestDto){
        VariationResponseDto variationResponseDto = variationService.createVariation(variationRequestDto);
        return new ResponseEntity<>(variationResponseDto, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Variation doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of variation", content = @Content(schema = @Schema(implementation = VariationResponseDto.class))),
    })
    @Operation(summary = "Retrieve Variation", description = "Returns a variation based on id" )
    @GetMapping("/{variationId}")
    public ResponseEntity<VariationResponseDto> getVariationById(@Valid @PathVariable Long variationId){
        VariationResponseDto variationResponseDto = variationService.getVariationById(variationId);
        return new ResponseEntity<>(variationResponseDto, HttpStatus.OK);
    }
    @Operation(summary = "Retrieve Variations", description = "Provides a list of all variations" )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of variations", content = @Content(array = @ArraySchema(schema = @Schema(implementation = VariationResponseDto.class))))
    @GetMapping("/all")
    public ResponseEntity<List<VariationResponseDto>> getAllVariations(){
        List<VariationResponseDto> variationResponseDtos = variationService.getVariations();
        return new ResponseEntity<>(variationResponseDtos, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Variation doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful deletion of variation")
    })
    @Operation(summary = "Deletes Variation", description = "Deletes a variation based on id" )
    @DeleteMapping("/delete/{variationId}")
    public ResponseEntity<Void> deleteVariation(@PathVariable Long variationId){
        variationService.deleteVariation(variationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Variation doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful edit of variation", content = @Content(schema = @Schema(implementation = VariationResponseDto.class))),
    })
    @Operation(summary = "Edit Variation", description = "Edits a variation from the provided payload and id")
    @PutMapping("/edit/{variationId}")
    public ResponseEntity<VariationResponseDto> editVariation(@PathVariable Long variationId,
                                                              @Valid @RequestBody VariationRequestDto variationRequestDto){
        VariationResponseDto variationResponseDto = variationService.editVariation(variationId, variationRequestDto);
        return new ResponseEntity<>(variationResponseDto, HttpStatus.OK);
    }
}

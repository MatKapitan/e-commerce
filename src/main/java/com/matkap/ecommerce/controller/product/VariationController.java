package com.matkap.ecommerce.controller.product;

import com.matkap.ecommerce.dto.requestDto.product.VariationRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.VariationResponseDto;
import com.matkap.ecommerce.service.product.VariationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variation")
public class VariationController {


    private final VariationService variationService;

    public VariationController(VariationService variationService) {
        this.variationService = variationService;
    }


    @PostMapping("/create")
    public ResponseEntity<VariationResponseDto> addVariation(@Valid @RequestBody VariationRequestDto variationRequestDto){
        VariationResponseDto variationResponseDto = variationService.createVariation(variationRequestDto);
        return new ResponseEntity<>(variationResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{variationId}")
    public ResponseEntity<VariationResponseDto> getVariationById(@Valid @PathVariable Long variationId){
        VariationResponseDto variationResponseDto = variationService.getVariationById(variationId);
        return new ResponseEntity<>(variationResponseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VariationResponseDto>> getAllVariations(){
        List<VariationResponseDto> variationResponseDtos = variationService.getVariations();
        return new ResponseEntity<>(variationResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{variationId}")
    public ResponseEntity<Void> deleteVariation(@PathVariable Long variationId){
        variationService.deleteVariation(variationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{variationId}")
    public ResponseEntity<VariationResponseDto> editVariation(@PathVariable Long variationId,
                                                              @Valid @RequestBody VariationRequestDto variationRequestDto){
        VariationResponseDto variationResponseDto = variationService.editVariation(variationId, variationRequestDto);
        return new ResponseEntity<>(variationResponseDto, HttpStatus.OK);
    }
}

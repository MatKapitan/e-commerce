package com.matkap.ecommerce.controller.product;

import com.matkap.ecommerce.dto.requestDto.product.VariationOptionRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.VariationOptionResponseDto;
import com.matkap.ecommerce.service.product.VariationOptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variation-option")
public class VariationOptionController {

private final VariationOptionService variationOptionService;

    public VariationOptionController(VariationOptionService variationOptionService) {
        this.variationOptionService = variationOptionService;
    }

    @PostMapping("/create")
    public ResponseEntity<VariationOptionResponseDto> addVariationOption(@Valid @RequestBody VariationOptionRequestDto variationOptionRequestDto){
        VariationOptionResponseDto variationOptionResponseDto = variationOptionService.createVariationOption(variationOptionRequestDto);
        return new ResponseEntity<>(variationOptionResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{variationOptionId}")
    public ResponseEntity<VariationOptionResponseDto> getVariationOptionById(@PathVariable Long variationOptionId){
        VariationOptionResponseDto variationOptionResponseDto = variationOptionService.getVariationOptionById(variationOptionId);
        return new ResponseEntity<>(variationOptionResponseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VariationOptionResponseDto>> getAllVariationOptions(){
        List<VariationOptionResponseDto> variationOptionsResponseDto = variationOptionService.getVariationOptions();
        return new ResponseEntity<>(variationOptionsResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{variationOptionId}")
    public ResponseEntity<VariationOptionResponseDto> deleteVariationOption(@PathVariable Long variationOptionId){
        variationOptionService.deleteVariationOption(variationOptionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{variationOptionId}")
    public ResponseEntity<VariationOptionResponseDto> editVariationOption(@PathVariable Long variationOptionId,
                                                                          @Valid @RequestBody VariationOptionRequestDto variationOptionRequestDto){
        VariationOptionResponseDto variationOptionResponseDto = variationOptionService.editVariationOption(variationOptionId, variationOptionRequestDto);
        return new ResponseEntity<>(variationOptionResponseDto, HttpStatus.OK);
    }
}

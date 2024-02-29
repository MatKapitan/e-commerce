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

    @PostMapping("/add")
    public ResponseEntity<VariationOptionResponseDto> addVariationOption(@Valid @RequestBody VariationOptionRequestDto variationOptionRequestDto){
        VariationOptionResponseDto variationOptionResponseDto = variationOptionService.createVariationOption(variationOptionRequestDto);
        return new ResponseEntity<>(variationOptionResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VariationOptionResponseDto> getVariationOptionById(@PathVariable Long id){
        VariationOptionResponseDto variationOptionResponseDto = variationOptionService.getVariationOptionById(id);
        return new ResponseEntity<>(variationOptionResponseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VariationOptionResponseDto>> getAllVariationOptions(){
        List<VariationOptionResponseDto> variationOptionsResponseDto = variationOptionService.getVariationOptions();
        return new ResponseEntity<>(variationOptionsResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<VariationOptionResponseDto> deleteVariationOption(@PathVariable Long id){
        variationOptionService.deleteVariationOption(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<VariationOptionResponseDto> editVariationOption(@PathVariable Long id,
                                                                          @Valid @RequestBody VariationOptionRequestDto variationOptionRequestDto){
        VariationOptionResponseDto variationOptionResponseDto = variationOptionService.editVariationOption(id, variationOptionRequestDto);
        return new ResponseEntity<>(variationOptionResponseDto, HttpStatus.OK);
    }
}

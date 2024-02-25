package com.matkap.ecommerce.controller.product;

import com.matkap.ecommerce.dto.requestDto.product.VariationRequestDto;
import com.matkap.ecommerce.dto.responseDto.product.VariationResponseDto;
import com.matkap.ecommerce.service.product.VariationService;
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


    @GetMapping("/{id}")
    public ResponseEntity<VariationResponseDto> getVariationById(@PathVariable Long id){
        VariationResponseDto variationResponseDto = variationService.getVariationById(id);
        return new ResponseEntity<>(variationResponseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VariationResponseDto>> getAllVariations(){
        List<VariationResponseDto> variationResponseDtos = variationService.getVariations();
        return new ResponseEntity<>(variationResponseDtos, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<VariationResponseDto> addVariation(@RequestBody VariationRequestDto variationRequestDto){
        VariationResponseDto variationResponseDto = variationService.createVariation(variationRequestDto);
        return new ResponseEntity<>(variationResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<VariationResponseDto> deleteVariation(@PathVariable Long id){
        VariationResponseDto variationResponseDto = variationService.deleteVariation(id);
        return new ResponseEntity<>(variationResponseDto, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<VariationResponseDto> editVariation(@PathVariable Long id,
                                                              @RequestBody VariationRequestDto variationRequestDto){
        VariationResponseDto variationResponseDto = variationService.editVariation(id, variationRequestDto);
        return new ResponseEntity<>(variationResponseDto, HttpStatus.OK);
    }
}

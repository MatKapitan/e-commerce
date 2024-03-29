package com.matkap.ecommerce.controller.product;


import com.matkap.ecommerce.dto.requestDto.product.PromotionRequestDto;
import com.matkap.ecommerce.model.product.Promotion;
import com.matkap.ecommerce.service.product.PromotionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }



    @PostMapping("/create")
    public ResponseEntity<Promotion> createPromotion(@Valid @RequestBody PromotionRequestDto promotionRequestDto){
        Promotion promotion = promotionService.createPromotion(promotionRequestDto);
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }

    @GetMapping("/{promotionId}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable Long promotionId){
        Promotion promotion = promotionService.getPromotionById(promotionId);
        return new ResponseEntity<>(promotion, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<Page<Promotion>> getAllPromotions(@PageableDefault(value = 5, page = 0)  Pageable pageable){
        Page<Promotion> promotions = promotionService.getAllPromotions(pageable);
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{promotionId}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long promotionId){
        promotionService.deletePromotion(promotionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/edit/{promotionId}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable Long promotionId,
                                                     @Valid @RequestBody PromotionRequestDto promotionRequestDto){
        Promotion promotion = promotionService.updatePromotion(promotionRequestDto, promotionId);
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }

    @PutMapping("/{promotionId}/add/product-category/{productCategoryId}")
    public ResponseEntity<Promotion> addProductCategory(@PathVariable Long promotionId,
                                                        @PathVariable Long productCategoryId){
        Promotion promotion = promotionService.addProductCategoryToPromotion(promotionId, productCategoryId);
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }

    @PutMapping("/{promotionId}/remove/product-category/{productCategoryId}")
    public ResponseEntity<Promotion> removeProductCategory(@PathVariable Long promotionId,
                                                            @PathVariable Long productCategoryId){
        Promotion promotion = promotionService.removeProductCategoryFromPromotion(promotionId, productCategoryId);
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }







}

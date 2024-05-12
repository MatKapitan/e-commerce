package com.matkap.ecommerce.service.product;

import com.matkap.ecommerce.dto.requestDto.product.PromotionRequestDto;
import com.matkap.ecommerce.model.product.Promotion;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PromotionService {


    PromotionRequestDto createPromotion(PromotionRequestDto promotionRequestDto);
    PromotionRequestDto getPromotionById(Long promotionId);
    List<PromotionRequestDto> getAllPromotions();
    Promotion getPromotionOrThrow(Long promotionId);
    void deletePromotion(Long promotionId);
    PromotionRequestDto updatePromotion(PromotionRequestDto promotionRequestDto, Long promotionId);

    PromotionRequestDto addProductCategoryToPromotion(Long promotionId, Long productCategoryId);
    PromotionRequestDto removeProductCategoryFromPromotion(Long promotionId, Long productCategoryId);

}

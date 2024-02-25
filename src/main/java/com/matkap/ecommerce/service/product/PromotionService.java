package com.matkap.ecommerce.service.product;

import com.matkap.ecommerce.dto.requestDto.product.PromotionRequestDto;
import com.matkap.ecommerce.model.product.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PromotionService {


    Promotion createPromotion(PromotionRequestDto promotionRequestDto);
    Promotion getPromotionById(Long promotionId);
    Page<Promotion> getAllPromotions(Pageable pageable);
    Promotion getPromotionOrThrow(Long promotionId);
    void deletePromotion(Long promotionId);
    Promotion updatePromotion(PromotionRequestDto promotionRequestDto, Long promotionId);

    Promotion addProductCategoryToPromotion(Long promotionId, Long productCategoryId);
    Promotion removeProductCategoryFromPromotion(Long promotionId, Long productCategoryId);

}

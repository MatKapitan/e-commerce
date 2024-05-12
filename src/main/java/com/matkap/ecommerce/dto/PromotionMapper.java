package com.matkap.ecommerce.dto;

import com.matkap.ecommerce.dto.requestDto.product.PromotionRequestDto;
import com.matkap.ecommerce.model.product.Promotion;
import org.springframework.stereotype.Component;

@Component
public class PromotionMapper implements IMapper<Promotion, PromotionRequestDto>{
    @Override
    public PromotionRequestDto toDto(Promotion entity) {
        PromotionRequestDto promotionRequestDto = new PromotionRequestDto();
        promotionRequestDto.setName(entity.getName());
        promotionRequestDto.setDescription(entity.getDescription());
        promotionRequestDto.setDiscountRate(entity.getDiscountRate());
        promotionRequestDto.setStartDate(entity.getStartDate());
        promotionRequestDto.setEndDate(entity.getEndDate());
        return promotionRequestDto;
    }

    @Override
    public Promotion toEntity(PromotionRequestDto dto) {
        Promotion promotion = new Promotion();
        promotion.setName(dto.getName());
        promotion.setDescription(dto.getDescription());
        promotion.setDiscountRate(dto.getDiscountRate());
        promotion.setStartDate(dto.getStartDate());
        promotion.setEndDate(dto.getEndDate());
        return promotion;
    }
}

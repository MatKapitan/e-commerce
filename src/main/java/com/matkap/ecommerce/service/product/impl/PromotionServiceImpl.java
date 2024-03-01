package com.matkap.ecommerce.service.product.impl;

import com.matkap.ecommerce.dto.requestDto.product.PromotionRequestDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.product.ProductCategory;
import com.matkap.ecommerce.model.product.Promotion;
import com.matkap.ecommerce.repository.product.PromotionRepository;
import com.matkap.ecommerce.service.product.ProductCategoryService;
import com.matkap.ecommerce.service.product.PromotionService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final ProductCategoryService productCategoryService;

    public PromotionServiceImpl(PromotionRepository promotionRepository, ProductCategoryService productCategoryService) {
        this.promotionRepository = promotionRepository;
        this.productCategoryService = productCategoryService;
    }


    @Override
    public Promotion createPromotion(PromotionRequestDto promotionRequestDto) {
        Promotion promotion = new Promotion();
        promotion.setName(promotionRequestDto.getName());
        promotion.setDescription(promotionRequestDto.getDescription());
        promotion.setDiscountRate(promotionRequestDto.getDiscountRate());
        promotion.setStartDate(promotionRequestDto.getStartDate());
        promotion.setEndDate(promotionRequestDto.getEndDate());

        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion getPromotionById(Long promotionId) {
        return getPromotionOrThrow(promotionId);
    }

    @Override
    public Page<Promotion> getAllPromotions(Pageable pageable) {
        return promotionRepository.findAll(pageable);
    }

    @Override
    public Promotion getPromotionOrThrow(Long promotionId) {
        return promotionRepository.findById(promotionId).orElseThrow(
                () -> new EntityNotFoundException(promotionId, Promotion.class));
    }

    @Override
    @Transactional
    public void deletePromotion(Long promotionId) {
        Promotion promotion = getPromotionOrThrow(promotionId);
        promotionRepository.delete(promotion);
    }

    @Override
    public Promotion updatePromotion(PromotionRequestDto promotionRequestDto, Long promotionId) {
        Promotion promotion = getPromotionOrThrow(promotionId);
        promotion.setName(promotionRequestDto.getName());
        promotion.setDescription(promotionRequestDto.getDescription());
        promotion.setDiscountRate(promotionRequestDto.getDiscountRate());
        promotion.setStartDate(promotionRequestDto.getStartDate());
        promotion.setEndDate(promotionRequestDto.getEndDate());

        promotionRepository.save(promotion);
        return promotion;
    }

//TODO add product Category


    @Override
    public Promotion addProductCategoryToPromotion(Long promotionId, Long productCategoryId) {
        Promotion promotion = getPromotionOrThrow(promotionId);
        ProductCategory productCategory = productCategoryService.getProductCategory(productCategoryId);
        promotion.getProductCategory().add(productCategory);
        promotionRepository.save(promotion);
        return promotion;
    }

    @Override
    public Promotion removeProductCategoryFromPromotion(Long promotionId, Long productCategoryId) {
        Promotion promotion = getPromotionOrThrow(promotionId);
        ProductCategory productCategory = productCategoryService.getProductCategory(productCategoryId);
        promotion.getProductCategory().remove(productCategory);
        promotionRepository.save(promotion);
        return promotion;
    }
}
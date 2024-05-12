package com.matkap.ecommerce.service.product.impl;

import com.matkap.ecommerce.dto.PromotionMapper;
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

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final ProductCategoryService productCategoryService;
    private final PromotionMapper promotionMapper;

    public PromotionServiceImpl(PromotionRepository promotionRepository, ProductCategoryService productCategoryService, PromotionMapper promotionMapper) {
        this.promotionRepository = promotionRepository;
        this.productCategoryService = productCategoryService;
        this.promotionMapper = promotionMapper;
    }


    @Override
    public PromotionRequestDto createPromotion(PromotionRequestDto promotionRequestDto) {
        Promotion promotion = promotionMapper.toEntity(promotionRequestDto);
        return promotionMapper.toDto(promotionRepository.save(promotion));
    }

    @Override
    public PromotionRequestDto getPromotionById(Long promotionId) {
        return promotionMapper.toDto(getPromotionOrThrow(promotionId));
    }

    @Override
    public List<PromotionRequestDto> getAllPromotions() {
        return promotionMapper.toDtoList(promotionRepository.findAll());
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
    public PromotionRequestDto updatePromotion(PromotionRequestDto promotionRequestDto, Long promotionId) {
        Promotion promotion = getPromotionOrThrow(promotionId);
        promotion.setName(promotionRequestDto.getName());
        promotion.setDescription(promotionRequestDto.getDescription());
        promotion.setDiscountRate(promotionRequestDto.getDiscountRate());
        promotion.setStartDate(promotionRequestDto.getStartDate());
        promotion.setEndDate(promotionRequestDto.getEndDate());

        promotionRepository.save(promotion);
        return promotionMapper.toDto(promotion);
    }

    @Override
    public PromotionRequestDto addProductCategoryToPromotion(Long promotionId, Long productCategoryId) {
        Promotion promotion = getPromotionOrThrow(promotionId);
        ProductCategory productCategory = productCategoryService.getProductCategory(productCategoryId);
        promotion.getProductCategory().add(productCategory);
        promotionRepository.save(promotion);
        return promotionMapper.toDto(promotion);
    }

    @Override
    public PromotionRequestDto removeProductCategoryFromPromotion(Long promotionId, Long productCategoryId) {
        Promotion promotion = getPromotionOrThrow(promotionId);
        ProductCategory productCategory = productCategoryService.getProductCategory(productCategoryId);
        promotion.getProductCategory().remove(productCategory);
        promotionRepository.save(promotion);
        return promotionMapper.toDto(promotion);
    }
}
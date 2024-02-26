package com.matkap.ecommerce.service.shopingCard.impl;

import com.matkap.ecommerce.dto.requestDto.shoppingCard.ShoppingCardRequestDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.product.ProductItem;
import com.matkap.ecommerce.model.shopingCard.ShoppingCard;
import com.matkap.ecommerce.model.user.SiteUser;
import com.matkap.ecommerce.repository.shoppingCard.ShoppingCardRepository;
import com.matkap.ecommerce.service.product.ProductItemService;
import com.matkap.ecommerce.service.shopingCard.ShoppingCardService;
import com.matkap.ecommerce.service.user.SiteUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCardServiceImpl implements ShoppingCardService {

    private final ShoppingCardRepository shoppingCardRepository;
    private final SiteUserService siteUserService;
    private final ProductItemService productItemService;

    public ShoppingCardServiceImpl(ShoppingCardRepository shoppingCardRepository, SiteUserService siteUserService, ProductItemService productItemService) {
        this.shoppingCardRepository = shoppingCardRepository;
        this.siteUserService = siteUserService;
        this.productItemService = productItemService;
    }


    @Override
    public ShoppingCard createShoppingCard(ShoppingCardRequestDto shoppingCardRequestDto) {
        ShoppingCard shoppingCard = new ShoppingCard();
        SiteUser siteUser = siteUserService.getSiteUser(shoppingCardRequestDto.getSiteUserId());
        shoppingCard.setSiteUser(siteUser);
        ProductItem productItem = productItemService.getProductItem(shoppingCardRequestDto.getProductItemId());
        shoppingCard.setProductItem(productItem);
        shoppingCard.setQuantity(shoppingCardRequestDto.getQuantity());
        return shoppingCardRepository.save(shoppingCard);
    }

    @Override
    public List<ShoppingCard> getShoppingCards() {
        return shoppingCardRepository.findAll();
    }

    @Override
    public List<ShoppingCard> getShoppingCardsBySiteUser(Long siteUserId) {
        SiteUser siteUser = siteUserService.getSiteUser(siteUserId);
        return shoppingCardRepository.findBySiteUser(siteUser);
    }

    @Override
    public ShoppingCard getShoppingCardById(Long shoppingCardId) {
        return getShoppingCard(shoppingCardId);
    }

    @Override
    public ShoppingCard getShoppingCard(Long shoppingCardId) {
        return shoppingCardRepository.findById(shoppingCardId).orElseThrow(
                () -> new EntityNotFoundException(shoppingCardId, ShoppingCard.class));
    }

    @Override
    public void deleteShoppingCard(Long shoppingCardId) {
        ShoppingCard shoppingCard = getShoppingCard(shoppingCardId);
        shoppingCardRepository.delete(shoppingCard);
    }

    @Override
    public ShoppingCard editShoppingCard(Long shoppingCardId, ShoppingCardRequestDto shoppingCardRequestDto) {
        ShoppingCard shoppingCard = getShoppingCard(shoppingCardId);
        SiteUser siteUser = siteUserService.getSiteUser(shoppingCardRequestDto.getSiteUserId());
        shoppingCard.setSiteUser(siteUser);
        ProductItem productItem = productItemService.getProductItem(shoppingCardRequestDto.getProductItemId());
        shoppingCard.setProductItem(productItem);
        shoppingCard.setQuantity(shoppingCardRequestDto.getQuantity());
        return shoppingCardRepository.save(shoppingCard);
    }
}

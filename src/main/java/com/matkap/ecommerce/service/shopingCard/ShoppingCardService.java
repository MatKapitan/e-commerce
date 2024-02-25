package com.matkap.ecommerce.service.shopingCard;

import com.matkap.ecommerce.dto.requestDto.shoppingCard.ShoppingCardRequestDto;
import com.matkap.ecommerce.model.shopingCard.ShoppingCard;

import java.util.List;

public interface ShoppingCardService {


    public ShoppingCard createShoppingCard(ShoppingCardRequestDto shoppingCardItemRequestDto);
    public List<ShoppingCard> getShoppingCards();
    public ShoppingCard getShoppingCardById(Long shoppingCardId);
    public ShoppingCard getShoppingCard(Long shoppingCardId);
    public void deleteShoppingCard(Long shoppingCardId);
    public ShoppingCard editShoppingCard(Long shoppingCardId, ShoppingCardRequestDto shoppingCardItemRequestDto);
}

package com.matkap.ecommerce.repository.shoppingCard;

import com.matkap.ecommerce.model.shopingCard.ShoppingCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCardRepository extends JpaRepository<ShoppingCard, Long> {
}

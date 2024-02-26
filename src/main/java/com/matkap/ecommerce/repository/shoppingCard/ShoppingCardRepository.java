package com.matkap.ecommerce.repository.shoppingCard;

import com.matkap.ecommerce.model.shopingCard.ShoppingCard;
import com.matkap.ecommerce.model.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingCardRepository extends JpaRepository<ShoppingCard, Long> {
    @Query("select s from ShoppingCard s where s.siteUser = ?1")
    List<ShoppingCard> findBySiteUser(SiteUser siteUser);




}

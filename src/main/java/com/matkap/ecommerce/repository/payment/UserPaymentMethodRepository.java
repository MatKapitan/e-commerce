package com.matkap.ecommerce.repository.payment;

import com.matkap.ecommerce.model.payment.UserPaymentMethod;
import com.matkap.ecommerce.model.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserPaymentMethodRepository extends JpaRepository<UserPaymentMethod, Long> {
    @Query("select u from UserPaymentMethod u where u.siteUser.id = ?1 and u.defaultPayment = ?2")
    List<UserPaymentMethod> findCurrentDefaultPaymentByUser(Long id, Boolean defaultPayment);

    @Transactional
    @Modifying
    @Query("update UserPaymentMethod u set u.defaultPayment = false where u.siteUser.id = ?1")
    void updateDefaultPaymentBySiteUser(Long siteUserId);




}

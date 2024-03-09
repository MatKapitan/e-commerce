package com.matkap.ecommerce.repository.payment;

import com.matkap.ecommerce.model.payment.UserPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPaymentMethodRepository extends JpaRepository<UserPaymentMethod, Long> {
    @Query("select u from UserPaymentMethod u where u.siteUser.id = ?1 and u.defaultPayment = ?2")
    List<UserPaymentMethod> findCurrentDefaultPaymentByUser(Long id, Boolean defaultPayment);


}

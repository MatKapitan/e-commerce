package com.matkap.ecommerce.repository.payment;

import com.matkap.ecommerce.model.payment.UserPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentMethodRepository extends JpaRepository<UserPaymentMethod, Long> {
}

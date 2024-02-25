package com.matkap.ecommerce.repository.payment;

import com.matkap.ecommerce.model.payment.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
}

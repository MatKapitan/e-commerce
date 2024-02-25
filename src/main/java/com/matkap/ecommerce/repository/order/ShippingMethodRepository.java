package com.matkap.ecommerce.repository.order;

import com.matkap.ecommerce.model.order.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingMethodRepository extends JpaRepository<ShippingMethod,Long> {
}

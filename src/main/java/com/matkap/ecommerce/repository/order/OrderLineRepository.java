package com.matkap.ecommerce.repository.order;

import com.matkap.ecommerce.model.order.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}

package com.matkap.ecommerce.repository.order;

import com.matkap.ecommerce.model.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    OrderStatus findByStatus(String status);


}

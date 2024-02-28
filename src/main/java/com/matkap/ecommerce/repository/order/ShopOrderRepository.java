package com.matkap.ecommerce.repository.order;

import com.matkap.ecommerce.model.order.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {


}

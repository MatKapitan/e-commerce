package com.matkap.ecommerce.repository.order;

import com.matkap.ecommerce.model.order.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {


}

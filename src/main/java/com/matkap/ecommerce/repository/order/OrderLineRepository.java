package com.matkap.ecommerce.repository.order;

import com.matkap.ecommerce.model.order.OrderLine;
import com.matkap.ecommerce.model.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}

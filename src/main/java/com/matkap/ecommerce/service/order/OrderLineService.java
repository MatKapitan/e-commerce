package com.matkap.ecommerce.service.order;

import com.matkap.ecommerce.model.order.OrderLine;
import com.matkap.ecommerce.model.order.ShopOrder;

import java.math.BigDecimal;
import java.util.List;

public interface OrderLineService {

    public BigDecimal shoppingCardToShopOrder(Long siteUserId, ShopOrder shopOrder);
    public List<OrderLine> getOrderLines();
    public OrderLine getOrderLineById(Long orderLineId);
    public OrderLine getOrderLine(Long orderLineId);
    public void deleteOrderLine(Long orderLineId);
}

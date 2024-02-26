package com.matkap.ecommerce.service.order;

import com.matkap.ecommerce.dto.requestDto.order.OrderLineRequestDto;
import com.matkap.ecommerce.model.order.OrderLine;

import java.util.List;

public interface OrderLineService {

    public void shoppingCardToShopOrder(Long siteUserId, Long shopOrderId);
    public List<OrderLine> getOrderLines();
    public OrderLine getOrderLineById(Long orderLineId);
    public OrderLine getOrderLine(Long orderLineId);
    public void deleteOrderLine(Long orderLineId);
}

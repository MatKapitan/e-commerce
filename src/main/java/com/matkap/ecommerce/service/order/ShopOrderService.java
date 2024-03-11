package com.matkap.ecommerce.service.order;

import com.matkap.ecommerce.dto.requestDto.order.ShopOrderRequestDto;
import com.matkap.ecommerce.model.order.ShopOrder;

import java.util.List;

public interface ShopOrderService {

    public ShopOrder createShopOrder(ShopOrderRequestDto shopOrderRequestDto);
    public List<ShopOrder> getShopOrders();
    public ShopOrder getShopOrderById(Long shopOrderId);
    public ShopOrder getShopOrder(Long shopOrderId);
    public void deleteShopOrder(Long shopOrderId);
}

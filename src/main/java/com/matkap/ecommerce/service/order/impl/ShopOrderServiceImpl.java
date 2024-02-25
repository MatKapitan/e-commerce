package com.matkap.ecommerce.service.order.impl;


import com.matkap.ecommerce.dto.requestDto.order.ShopOrderRequestDto;
import com.matkap.ecommerce.model.order.ShopOrder;
import com.matkap.ecommerce.model.user.SiteUser;
import com.matkap.ecommerce.repository.order.OrderStatusRepository;
import com.matkap.ecommerce.repository.order.ShippingMethodRepository;
import com.matkap.ecommerce.repository.order.ShopOrderRepository;
import com.matkap.ecommerce.service.order.ShopOrderService;
import com.matkap.ecommerce.service.user.AddressService;
import com.matkap.ecommerce.service.user.SiteUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {

    private final ShopOrderRepository shopOrderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ShippingMethodRepository shippingMethodRepository;
    private final AddressService addressService;

    private final SiteUserService siteUserService;



    public ShopOrderServiceImpl(ShopOrderRepository shopOrderRepository, OrderStatusRepository orderStatusRepository, AddressService addressService, ShippingMethodRepository shippingMethodRepository, SiteUserService siteUserService) {
        this.shopOrderRepository = shopOrderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.addressService = addressService;
        this.shippingMethodRepository = shippingMethodRepository;
        this.siteUserService = siteUserService;
    }

    @Override
    public ShopOrder createShopOrder(ShopOrderRequestDto shopOrderRequestDto) {
        ShopOrder shopOrder = new ShopOrder();
        SiteUser siteUser = siteUserService.getSiteUser(shopOrderRequestDto.get)

        return null;
    }

    @Override
    public List<ShopOrder> getShopOrders() {
        return null;
    }

    @Override
    public ShopOrder getShopOrderById(Long shopOrderId) {
        return null;
    }

    @Override
    public ShopOrder getShopOrder(Long shopOrderId) {
        return null;
    }

    @Override
    public void deleteShopOrder(Long shopOrderId) {

    }

    @Override
    public ShopOrder editShopOrder(Long shopOrderId, ShopOrderRequestDto shopOrderRequestDto) {
        return null;
    }
}

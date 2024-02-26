package com.matkap.ecommerce.service.order.impl;


import com.matkap.ecommerce.dto.requestDto.order.ShopOrderRequestDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.order.ShopOrder;
import com.matkap.ecommerce.repository.order.OrderStatusRepository;
import com.matkap.ecommerce.repository.order.ShippingMethodRepository;
import com.matkap.ecommerce.repository.order.ShopOrderRepository;
import com.matkap.ecommerce.service.order.ShopOrderService;
import com.matkap.ecommerce.service.payment.UserPaymentMethodService;
import com.matkap.ecommerce.service.user.AddressService;
import com.matkap.ecommerce.service.user.SiteUserService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {

    private final ShopOrderRepository shopOrderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ShippingMethodRepository shippingMethodRepository;
    private final AddressService addressService;
    private final SiteUserService siteUserService;
    private final UserPaymentMethodService userPaymentMethodService;



    public ShopOrderServiceImpl(ShopOrderRepository shopOrderRepository, OrderStatusRepository orderStatusRepository, AddressService addressService, ShippingMethodRepository shippingMethodRepository, SiteUserService siteUserService, UserPaymentMethodService userPaymentMethodService) {
        this.shopOrderRepository = shopOrderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.addressService = addressService;
        this.shippingMethodRepository = shippingMethodRepository;
        this.siteUserService = siteUserService;
        this.userPaymentMethodService = userPaymentMethodService;
    }

    @Override
    public ShopOrder createShopOrder(ShopOrderRequestDto shopOrderRequestDto) {
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setOrder_date(new Timestamp(System.currentTimeMillis()));
        shopOrder.setOrderStatus(orderStatusRepository.findByStatus("Drafted"));
        shopOrder.setSiteUser(siteUserService.getSiteUser(shopOrderRequestDto.getSiteUserId()));
        shopOrder.setAddress(addressService.getAddress(shopOrderRequestDto.getAddressId()));
        shopOrder.setUserPaymentMethod(userPaymentMethodService.getUserPaymentMethod(shopOrderRequestDto.getUserPaymentMethodId()));  // TODO can create order without having to have payment method first

        return shopOrderRepository.save(shopOrder);
    }

    @Override
    public List<ShopOrder> getShopOrders() {
        return shopOrderRepository.findAll();
    }

    @Override
    public ShopOrder getShopOrderById(Long shopOrderId) {
        return getShopOrder(shopOrderId);
    }

    @Override
    public ShopOrder getShopOrder(Long shopOrderId) {
        return shopOrderRepository.findById(shopOrderId).orElseThrow(
                () -> new EntityNotFoundException(shopOrderId, ShopOrder.class));
    }

    @Override
    public void deleteShopOrder(Long shopOrderId) {
        ShopOrder shopOrder = getShopOrder(shopOrderId);
        shopOrderRepository.delete(shopOrder);
    }

    @Override
    public ShopOrder editShopOrder(Long shopOrderId, ShopOrderRequestDto shopOrderRequestDto) {
        ShopOrder shopOrder = getShopOrder(shopOrderId);
        shopOrder.setOrder_date(new Timestamp(System.currentTimeMillis()));
        shopOrder.setOrderStatus(orderStatusRepository.findByStatus("Drafted"));
        shopOrder.setSiteUser(siteUserService.getSiteUser(shopOrderRequestDto.getSiteUserId()));
        shopOrder.setAddress(addressService.getAddress(shopOrderRequestDto.getAddressId()));
        shopOrder.setUserPaymentMethod(userPaymentMethodService.getUserPaymentMethod(shopOrderRequestDto.getUserPaymentMethodId()));  // TODO can create order without having to have payment method first

        return shopOrderRepository.save(shopOrder);
    }
}

package com.matkap.ecommerce.service.order.impl;


import com.matkap.ecommerce.dto.requestDto.order.ShopOrderRequestDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.mail.MailService;
import com.matkap.ecommerce.model.order.ShippingMethod;
import com.matkap.ecommerce.model.order.ShopOrder;
import com.matkap.ecommerce.model.user.SiteUser;
import com.matkap.ecommerce.repository.order.OrderStatusRepository;
import com.matkap.ecommerce.repository.order.ShippingMethodRepository;
import com.matkap.ecommerce.repository.order.ShopOrderRepository;
import com.matkap.ecommerce.service.order.OrderLineService;
import com.matkap.ecommerce.service.order.ShopOrderService;
import com.matkap.ecommerce.service.payment.UserPaymentMethodService;
import com.matkap.ecommerce.service.user.AddressService;
import com.matkap.ecommerce.service.user.SiteUserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    private final OrderLineService orderLineService;

    private final MailService mailService;



    public ShopOrderServiceImpl(ShopOrderRepository shopOrderRepository, OrderStatusRepository orderStatusRepository, AddressService addressService, ShippingMethodRepository shippingMethodRepository, SiteUserService siteUserService, UserPaymentMethodService userPaymentMethodService, OrderLineService orderLineService, MailService mailService) {
        this.shopOrderRepository = shopOrderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.addressService = addressService;
        this.shippingMethodRepository = shippingMethodRepository;
        this.siteUserService = siteUserService;
        this.userPaymentMethodService = userPaymentMethodService;
        this.orderLineService = orderLineService;
        this.mailService = mailService;
    }

    @Transactional
    @Override
    public ShopOrder createShopOrder(ShopOrderRequestDto shopOrderRequestDto) {
        //create order
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
        shopOrder.setOrderStatus(orderStatusRepository.findByStatus("Ordered"));
        Long siteUserId = shopOrderRequestDto.getSiteUserId();
        SiteUser siteUser = siteUserService.getSiteUser(siteUserId);
        shopOrder.setSiteUser(siteUser);
        shopOrder.setAddress(addressService.getAddress(shopOrderRequestDto.getAddressId()));
        shopOrder.setUserPaymentMethod(userPaymentMethodService.getUserPaymentMethod(shopOrderRequestDto.getUserPaymentMethodId()));
        shopOrder.setShippingMethod(getShippingMethod(shopOrderRequestDto.getShippingMethodId()));
        shopOrderRepository.save(shopOrder);
        // shoppingCard into orderLine

        BigDecimal orderLineTotal = orderLineService.shoppingCardToShopOrder(siteUserId, shopOrder);
        BigDecimal shippingPrice = shopOrder.getShippingMethod().getPrice();
        BigDecimal orderTotal = orderLineTotal.add(shippingPrice);
        shopOrder.setOrderTotal(orderTotal);

        //send mail
        sendReceiptToUser(siteUser.getEmailAddress(),orderTotal.toString());
        return shopOrderRepository.save(shopOrder);
    }

    private void sendReceiptToUser(String emailAddress, String totalPrice){
        mailService.sendSimpleMessage(emailAddress, "Order receipt", "we received your order and your total price is " + totalPrice); // move to controller
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

    public ShippingMethod getShippingMethod(Long shippingMethodId){
        return shippingMethodRepository.findById(shippingMethodId).orElseThrow(
                () -> new EntityNotFoundException(shippingMethodId, ShippingMethod.class));
    }

    @Override
    public void deleteShopOrder(Long shopOrderId) {
        ShopOrder shopOrder = getShopOrder(shopOrderId);
        shopOrderRepository.delete(shopOrder);
    }

}

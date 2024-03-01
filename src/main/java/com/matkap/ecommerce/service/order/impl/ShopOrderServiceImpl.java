package com.matkap.ecommerce.service.order.impl;


import com.matkap.ecommerce.dto.requestDto.order.ShopOrderRequestDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.order.ShippingMethod;
import com.matkap.ecommerce.model.order.ShopOrder;
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



    public ShopOrderServiceImpl(ShopOrderRepository shopOrderRepository, OrderStatusRepository orderStatusRepository, AddressService addressService, ShippingMethodRepository shippingMethodRepository, SiteUserService siteUserService, UserPaymentMethodService userPaymentMethodService, OrderLineService orderLineService) {
        this.shopOrderRepository = shopOrderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.addressService = addressService;
        this.shippingMethodRepository = shippingMethodRepository;
        this.siteUserService = siteUserService;
        this.userPaymentMethodService = userPaymentMethodService;
        this.orderLineService = orderLineService;
    }

    @Transactional
    @Override
    public ShopOrder createShopOrder(ShopOrderRequestDto shopOrderRequestDto) {
        //create order
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
        shopOrder.setOrderStatus(orderStatusRepository.findByStatus("Ordered"));
        Long siteUserId = shopOrderRequestDto.getSiteUserId();
        shopOrder.setSiteUser(siteUserService.getSiteUser(siteUserId));
        shopOrder.setAddress(addressService.getAddress(shopOrderRequestDto.getAddressId()));
        shopOrder.setUserPaymentMethod(userPaymentMethodService.getUserPaymentMethod(shopOrderRequestDto.getUserPaymentMethodId()));  // TODO can create order without having to have payment method first
        shopOrder.setShippingMethod(getShippingMethod(shopOrderRequestDto.getShippingMethodId()));
        shopOrderRepository.save(shopOrder);
        // shoppingCard into orderLine

        BigDecimal orderLineTotal = orderLineService.shoppingCardToShopOrder(siteUserId, shopOrder);
//        ShopOrder shopOrderQ = getShopOrder(shopOrder.getId());  //TODO UHHHHHHHHHHHHHHHH order lines still null
//        List<OrderLine> orderLines = shopOrderQ.getOrderLines();
//        BigDecimal orderLineTotal = calculatePrice(orderLines);
        BigDecimal shippingPrice = shopOrder.getShippingMethod().getPrice();
        shopOrder.setOrderTotal(orderLineTotal.add(shippingPrice));
        return shopOrderRepository.save(shopOrder);
    }

//    private BigDecimal calculatePrice(List<OrderLine> orderLines){
//        BigDecimal total = BigDecimal.ZERO;
//        for (OrderLine orderline : orderLines) {
//            total = total.add(orderline.getPrice());
//        }
//        return total;
//    }


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

    @Override
    public ShopOrder editShopOrder(Long shopOrderId, ShopOrderRequestDto shopOrderRequestDto) {
        ShopOrder shopOrder = getShopOrder(shopOrderId);
        shopOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
        shopOrder.setOrderStatus(orderStatusRepository.findByStatus("Drafted"));
        shopOrder.setSiteUser(siteUserService.getSiteUser(shopOrderRequestDto.getSiteUserId()));
        shopOrder.setAddress(addressService.getAddress(shopOrderRequestDto.getAddressId()));
        shopOrder.setUserPaymentMethod(userPaymentMethodService.getUserPaymentMethod(shopOrderRequestDto.getUserPaymentMethodId()));  // TODO can create order without having to have payment method first

        return shopOrderRepository.save(shopOrder);
    }
}

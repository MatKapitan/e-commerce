package com.matkap.ecommerce.service.order.impl;


import com.matkap.ecommerce.dto.requestDto.order.OrderLineRequestDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.order.OrderLine;
import com.matkap.ecommerce.model.order.ShopOrder;
import com.matkap.ecommerce.model.product.ProductItem;
import com.matkap.ecommerce.model.shopingCard.ShoppingCard;
import com.matkap.ecommerce.repository.order.OrderLineRepository;
import com.matkap.ecommerce.service.order.OrderLineService;
import com.matkap.ecommerce.service.order.ShopOrderService;
import com.matkap.ecommerce.service.product.ProductItemService;
import com.matkap.ecommerce.service.shopingCard.ShoppingCardService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;

    private final ProductItemService productItemService;

    private final ShopOrderService shopOrderService;
    private final ShoppingCardService shoppingCardService;

    public OrderLineServiceImpl(OrderLineRepository orderLineRepository, ProductItemService productItemService, ShopOrderService shopOrderService, ShoppingCardService shoppingCardService) {
        this.orderLineRepository = orderLineRepository;
        this.productItemService = productItemService;
        this.shopOrderService = shopOrderService;
        this.shoppingCardService = shoppingCardService;
    }


    @Transactional
    public void shoppingCardToShopOrder(Long siteUserId, Long shopOrderId) {
        List<ShoppingCard> shoppingCardsBySiteUser = shoppingCardService.getShoppingCardsBySiteUser(siteUserId);
        for (ShoppingCard shoppingcard : shoppingCardsBySiteUser) {
            OrderLine orderLine = new OrderLine();
            orderLine.setShopOrder(shopOrderService.getShopOrder(shopOrderId));
            orderLine.setProductItem(shoppingcard.getProductItem());
            orderLine.setQuantity(shoppingcard.getQuantity()); // TODO price calculation

            orderLineRepository.save(orderLine);
            shoppingCardService.deleteShoppingCard(shoppingcard.getId());
        }
    }


    @Override
    public List<OrderLine> getOrderLines() {
        return orderLineRepository.findAll();
    }

    @Override
    public OrderLine getOrderLineById(Long orderLineId) {
        return getOrderLine(orderLineId);
    }

    @Override
    public OrderLine getOrderLine(Long orderLineId) {
        return orderLineRepository.findById(orderLineId).orElseThrow(
                () -> new EntityNotFoundException(orderLineId, OrderLine.class));
    }

    @Override
    public void deleteOrderLine(Long orderLineId) {
        OrderLine orderLine = getOrderLine(orderLineId);
        orderLineRepository.delete(orderLine);
    }
}

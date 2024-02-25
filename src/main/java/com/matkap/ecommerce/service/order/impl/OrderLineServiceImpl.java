package com.matkap.ecommerce.service.order.impl;


import com.matkap.ecommerce.dto.requestDto.order.OrderLineRequestDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.order.OrderLine;
import com.matkap.ecommerce.model.order.ShopOrder;
import com.matkap.ecommerce.model.product.ProductItem;
import com.matkap.ecommerce.repository.order.OrderLineRepository;
import com.matkap.ecommerce.service.order.OrderLineService;
import com.matkap.ecommerce.service.order.ShopOrderService;
import com.matkap.ecommerce.service.product.ProductItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;

    private final ProductItemService productItemService;

    private final ShopOrderService shopOrderService;

    public OrderLineServiceImpl(OrderLineRepository orderLineRepository, ProductItemService productItemService, ShopOrderService shopOrderService) {
        this.orderLineRepository = orderLineRepository;
        this.productItemService = productItemService;
        this.shopOrderService = shopOrderService;
    }


    @Override
    public OrderLine createOrderLine(OrderLineRequestDto orderLineRequestDto) {
        OrderLine orderLine = new OrderLine();
        ProductItem productItem = productItemService.getProductItem(orderLineRequestDto.getProductItemId());
        orderLine.setProductItem(productItem);
        ShopOrder shopOrder = shopOrderService.getShopOrder(orderLineRequestDto.getShopOrderId());
        orderLine.setShopOrder(shopOrder);
        orderLine.setQuantity(orderLineRequestDto.getQuantity());
        orderLine.setPrice(orderLineRequestDto.getPrice());
        return orderLineRepository.save(orderLine);
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

    @Override
    public OrderLine editOrderLine(Long orderLineId, OrderLineRequestDto orderLineRequestDto) {
        OrderLine orderLine = getOrderLine(orderLineId);
        ProductItem productItem = productItemService.getProductItem(orderLineRequestDto.getProductItemId());
        orderLine.setProductItem(productItem);
        ShopOrder shopOrder = shopOrderService.getShopOrder(orderLineRequestDto.getShopOrderId());
        orderLine.setShopOrder(shopOrder);
        orderLine.setQuantity(orderLineRequestDto.getQuantity());
        orderLine.setPrice(orderLineRequestDto.getPrice());
        return orderLineRepository.save(orderLine);
    }
}

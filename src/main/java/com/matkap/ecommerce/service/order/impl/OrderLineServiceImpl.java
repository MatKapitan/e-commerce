package com.matkap.ecommerce.service.order.impl;


import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.order.OrderLine;
import com.matkap.ecommerce.model.order.ShopOrder;
import com.matkap.ecommerce.model.product.ProductItem;
import com.matkap.ecommerce.model.product.Promotion;
import com.matkap.ecommerce.model.shopingCard.ShoppingCard;
import com.matkap.ecommerce.repository.order.OrderLineRepository;
import com.matkap.ecommerce.service.order.OrderLineService;
import com.matkap.ecommerce.service.shopingCard.ShoppingCardService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final ShoppingCardService shoppingCardService;

    public OrderLineServiceImpl(OrderLineRepository orderLineRepository, ShoppingCardService shoppingCardService) {
        this.orderLineRepository = orderLineRepository;
        this.shoppingCardService = shoppingCardService;
    }


    @Transactional
    public BigDecimal shoppingCardToShopOrder(Long siteUserId, ShopOrder shopOrder) {
        List<ShoppingCard> shoppingCardsBySiteUser = shoppingCardService.getShoppingCardsBySiteUser(siteUserId);
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ShoppingCard shoppingcard : shoppingCardsBySiteUser) {
            OrderLine orderLine = new OrderLine();
            orderLine.setShopOrder(shopOrder);
            orderLine.setProductItem(shoppingcard.getProductItem());
            orderLine.setQuantity(shoppingcard.getQuantity());
            BigDecimal price = calculatePrice(shoppingcard.getProductItem());
            orderLine.setPrice(price);

            totalPrice = totalPrice.add(price);
            OrderLine save = orderLineRepository.save(orderLine);
            shoppingCardService.deleteShoppingCard(shoppingcard.getId());
        }return totalPrice;
    }

    private BigDecimal calculatePrice(ProductItem productItem){
        BigDecimal price = productItem.getPrice();
        List<Promotion> promotions = productItem.getProduct().getProductCategory().getPromotions();
        if (promotions.isEmpty()) return price;

        Double maxDiscountRate = findMaxPromotion(promotions);
        return new BigDecimal(maxDiscountRate).multiply(price).setScale(2, RoundingMode.HALF_UP);
    }

    private Double findMaxPromotion(List<Promotion> promotions){
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Double maxDiscount = 1.00d;
        for (Promotion promotion : promotions) {
            if (promotion.getStart_date().after(currentTime) &&
                    promotion.getEnd_date().before(currentTime)){
                if (promotion.getDiscount_rate() > maxDiscount){
                    maxDiscount = promotion.getDiscount_rate();}
            }
        }
        return maxDiscount;
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

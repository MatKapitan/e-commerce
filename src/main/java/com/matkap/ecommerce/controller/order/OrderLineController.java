package com.matkap.ecommerce.controller.order;


import com.matkap.ecommerce.model.order.OrderLine;
import com.matkap.ecommerce.service.order.OrderLineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-line")
public class OrderLineController {

    private final OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @PostMapping("/user/{siteUserId}/order/{shopOrderId}")
    public ResponseEntity<Void> shoppingCardToShopOrder(@PathVariable Long siteUserId,
                                                        @PathVariable Long shopOrderId){
        orderLineService.shoppingCardToShopOrder(siteUserId, shopOrderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{orderLineId}")
    public ResponseEntity<OrderLine> getOrderLineByIdd (@PathVariable Long orderLineId){
        OrderLine orderLine = orderLineService.getOrderLineById(orderLineId);
        return new ResponseEntity<>(orderLine, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderLine>> getAllOrderLines(){
        List<OrderLine> orderLines = orderLineService.getOrderLines();
        return new ResponseEntity<>(orderLines, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderLineId}")
    public ResponseEntity<Void> deleteOrderLine(@PathVariable Long orderLineId){
        orderLineService.deleteOrderLine(orderLineId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

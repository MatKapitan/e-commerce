package com.matkap.ecommerce.controller.order;

import com.matkap.ecommerce.dto.requestDto.order.ShopOrderRequestDto;
import com.matkap.ecommerce.model.order.ShopOrder;
import com.matkap.ecommerce.service.order.ShopOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class ShopOrderController {


    private final ShopOrderService shopOrderService;

    public ShopOrderController(ShopOrderService shopOrderService) {
        this.shopOrderService = shopOrderService;
    }

    @PostMapping
    public ResponseEntity<ShopOrder> createShopOrder(@Valid @RequestBody ShopOrderRequestDto shopOrderRequestDto){
        ShopOrder shopOrder = shopOrderService.createShopOrder(shopOrderRequestDto);
        return new ResponseEntity<>(shopOrder, HttpStatus.OK);
    }

    @GetMapping("/{shopOderId}")
    public ResponseEntity<ShopOrder> getShopOrderById(@PathVariable Long shopOderId){
        ShopOrder shopOrder = shopOrderService.getShopOrderById(shopOderId);
        return new ResponseEntity<>(shopOrder, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShopOrder>> getAllShopOrders(){
        List<ShopOrder> shopOrders = shopOrderService.getShopOrders();
        return new ResponseEntity<>(shopOrders, HttpStatus.OK);
    }
}

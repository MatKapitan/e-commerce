package com.matkap.ecommerce.controller.order;

import com.matkap.ecommerce.dto.requestDto.order.ShopOrderRequestDto;
import com.matkap.ecommerce.exception.ErrorResponse;
import com.matkap.ecommerce.model.order.ShopOrder;
import com.matkap.ecommerce.service.order.ShopOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Shop Order Controller", description = "Create and retrieve shop orders" )
public class ShopOrderController {


    private final ShopOrderService shopOrderService;

    public ShopOrderController(ShopOrderService shopOrderService) {
        this.shopOrderService = shopOrderService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of shop order"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Create Shop order", description = "Creates a shop oder from the provided payload")
    @PostMapping("/create")
    public ResponseEntity<ShopOrder> createShopOrder(@Valid @RequestBody ShopOrderRequestDto shopOrderRequestDto){
        ShopOrder shopOrder = shopOrderService.createShopOrder(shopOrderRequestDto);
        return new ResponseEntity<>(shopOrder, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Shop order doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of shop order", content = @Content(schema = @Schema(implementation = ShopOrder.class))),
    })
    @Operation(summary = "Retrieve shop order", description = "Returns a shop order based on id" )
    @GetMapping("/{shopOderId}")
    public ResponseEntity<ShopOrder> getShopOrderById(@PathVariable Long shopOderId){
        ShopOrder shopOrder = shopOrderService.getShopOrderById(shopOderId);
        return new ResponseEntity<>(shopOrder, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Successful retrieval of contacts", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ShopOrder.class))))
    @Operation(summary = "Retrieve order lines", description = "Provides a list of all order lines" )
    @GetMapping("/all")
    public ResponseEntity<List<ShopOrder>> getAllShopOrders(){
        List<ShopOrder> shopOrders = shopOrderService.getShopOrders();
        return new ResponseEntity<>(shopOrders, HttpStatus.OK);
    }
}

package com.matkap.ecommerce.controller.order;


import com.matkap.ecommerce.exception.ErrorResponse;
import com.matkap.ecommerce.model.order.OrderLine;
import com.matkap.ecommerce.service.order.OrderLineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order-line", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Order line Controller", description = "Retrieve order lines" )
public class OrderLineController {

    private final OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Order line doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of order line", content = @Content(schema = @Schema(implementation = OrderLine.class))),
    })
    @Operation(summary = "Retrieve order line", description = "Returns a order line based on id" )
    @GetMapping("/{orderLineId}")
    public ResponseEntity<OrderLine> getOrderLineById(@PathVariable Long orderLineId){
        OrderLine orderLine = orderLineService.getOrderLineById(orderLineId);
        return new ResponseEntity<>(orderLine, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Successful retrieval of order line", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderLine.class))))
    @Operation(summary = "Retrieve all order lines", description = "Provides a list of all order lines" )
    @GetMapping("/all")
    public ResponseEntity<List<OrderLine>> getAllOrderLines(){
        List<OrderLine> orderLines = orderLineService.getOrderLines();
        return new ResponseEntity<>(orderLines, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Order line doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful deletion of order line")
    })
    @Operation(summary = "Deletes order line", description = "Deletes a order line based on id" )
    @DeleteMapping("/delete/{orderLineId}")
    public ResponseEntity<Void> deleteOrderLine(@PathVariable Long orderLineId){
        orderLineService.deleteOrderLine(orderLineId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //TODO possible remove this
}

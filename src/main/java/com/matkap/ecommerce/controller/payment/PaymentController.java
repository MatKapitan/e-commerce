package com.matkap.ecommerce.controller.payment;


import com.matkap.ecommerce.dto.requestDto.payment.UserPaymentMethodRequestDto;
import com.matkap.ecommerce.exception.ErrorResponse;
import com.matkap.ecommerce.model.payment.PaymentType;
import com.matkap.ecommerce.model.payment.UserPaymentMethod;
import com.matkap.ecommerce.service.payment.UserPaymentMethodService;
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
@RequestMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Payment Controller", description = "Retrieve payments" )
public class PaymentController {

    private final UserPaymentMethodService userPaymentMethodService;

    public PaymentController(UserPaymentMethodService userPaymentMethodService) {
        this.userPaymentMethodService = userPaymentMethodService;
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of payment method"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Create Payment method", description = "Creates a payment method from the provided payload")
    @PostMapping("/create")
    public ResponseEntity<UserPaymentMethod> createUserPaymentMethod(@Valid @RequestBody UserPaymentMethodRequestDto userPaymentMethodRequestDto){
        UserPaymentMethod userPaymentMethod = userPaymentMethodService.createUserPaymentMethod(userPaymentMethodRequestDto);
        return new ResponseEntity<>(userPaymentMethod, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Payment method doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of payment method", content = @Content(schema = @Schema(implementation = UserPaymentMethod.class))),
    })
    @Operation(summary = "Retrieve Payment method", description = "Returns a payment method based on id" )
    @GetMapping("/{paymentId}")
    public ResponseEntity<UserPaymentMethod> getUserPaymentMethodById(@PathVariable Long paymentId){
        UserPaymentMethod userPaymentMethod = userPaymentMethodService.getUserPaymentMethodById(paymentId);
        return new ResponseEntity<>(userPaymentMethod, HttpStatus.OK);
    }
    @Operation(summary = "Retrieve order lines", description = "Provides a list of all order lines" )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of contacts", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserPaymentMethod.class))))
    @GetMapping("/all")
    public ResponseEntity<List<UserPaymentMethod>> getAllUserPaymentMethods(){
        List<UserPaymentMethod> userPaymentMethods = userPaymentMethodService.getUserPaymentMethods();
        return new ResponseEntity<>(userPaymentMethods, HttpStatus.OK);
    }

    @GetMapping("/type/all")
    public ResponseEntity<List<PaymentType>> getAllPaymentTypes(){
        List<PaymentType> paymentTypes = userPaymentMethodService.getPaymentTypes();
        return new ResponseEntity<>(paymentTypes, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Payment method line doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful deletion of order payment method")
    })
    @Operation(summary = "Deletes Payment method", description = "Deletes a payment method based on id" )
    @DeleteMapping("/delete/{paymentId}")
    public ResponseEntity<Void> deleteUserPaymentMethod(@PathVariable Long paymentId){
        userPaymentMethodService.deleteUserPaymentMethod(paymentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/edit/{paymentId}")
    public ResponseEntity<UserPaymentMethod> editUserPaymentMethod(@PathVariable Long paymentId,
                                                                   @Valid @RequestBody UserPaymentMethodRequestDto userPaymentMethodRequestDto){
        UserPaymentMethod userPaymentMethod = userPaymentMethodService.editUserPaymentMethod(paymentId, userPaymentMethodRequestDto);
        return new ResponseEntity<>(userPaymentMethod, HttpStatus.OK);
    }
    @PutMapping("/set-default/{paymentId}")
    public ResponseEntity<Void> setDefault(@PathVariable Long paymentId){
        userPaymentMethodService.setDefaultPayment(paymentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

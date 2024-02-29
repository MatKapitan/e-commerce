package com.matkap.ecommerce.controller.payment;


import com.matkap.ecommerce.dto.requestDto.payment.UserPaymentMethodRequestDto;
import com.matkap.ecommerce.model.payment.PaymentType;
import com.matkap.ecommerce.model.payment.UserPaymentMethod;
import com.matkap.ecommerce.service.payment.UserPaymentMethodService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final UserPaymentMethodService userPaymentMethodService;

    public PaymentController(UserPaymentMethodService userPaymentMethodService) {
        this.userPaymentMethodService = userPaymentMethodService;
    }

    @PostMapping
    public ResponseEntity<UserPaymentMethod> createUserPaymentMethod(@Valid @RequestBody UserPaymentMethodRequestDto userPaymentMethodRequestDto){
        UserPaymentMethod userPaymentMethod = userPaymentMethodService.createUserPaymentMethod(userPaymentMethodRequestDto);
        return new ResponseEntity<>(userPaymentMethod, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPaymentMethod> getUserPaymentMethodById(@PathVariable Long id){
        UserPaymentMethod userPaymentMethod = userPaymentMethodService.getUserPaymentMethodById(id);
        return new ResponseEntity<>(userPaymentMethod, HttpStatus.OK);
    }

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserPaymentMethod(@PathVariable Long id){
        userPaymentMethodService.deleteUserPaymentMethod(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserPaymentMethod> editUserPaymentMethod(@PathVariable Long id,
                                                                   @Valid @RequestBody UserPaymentMethodRequestDto userPaymentMethodRequestDto){
        UserPaymentMethod userPaymentMethod = userPaymentMethodService.editUserPaymentMethod(id, userPaymentMethodRequestDto);
        return new ResponseEntity<>(userPaymentMethod, HttpStatus.OK);
    }

}

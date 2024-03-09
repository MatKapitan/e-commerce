package com.matkap.ecommerce.service.payment;

import com.matkap.ecommerce.dto.requestDto.payment.UserPaymentMethodRequestDto;
import com.matkap.ecommerce.model.payment.PaymentType;
import com.matkap.ecommerce.model.payment.UserPaymentMethod;

import java.util.List;

public interface UserPaymentMethodService {

    public UserPaymentMethod createUserPaymentMethod(UserPaymentMethodRequestDto userPaymentMethodRequestDto);
    public List<UserPaymentMethod> getUserPaymentMethods();
    public List<PaymentType> getPaymentTypes();
    public UserPaymentMethod getUserPaymentMethodById(Long userPaymentMethodId);
    public UserPaymentMethod getUserPaymentMethod(Long userPaymentMethodId);
    public PaymentType getPaymentType(Long paymentTypeId);
    public void deleteUserPaymentMethod(Long userPaymentMethodId);
    public UserPaymentMethod editUserPaymentMethod(Long userPaymentMethodId, UserPaymentMethodRequestDto userPaymentMethodRequestDto);
    public void  setDefaultPayment(Long userPaymentMethodId);
}

package com.matkap.ecommerce.dto.requestDto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ShopOrderRequestDto {

    @NotNull(message = "siteUserId cannot be null")
    private Long siteUserId;
    @NotNull(message = "userPaymentMethodId cannot be null")
    private Long userPaymentMethodId;
    @NotNull(message = "addressId cannot be null")
    private Long addressId;
    @NotNull(message = "shippingMethodId cannot be null")
    private Long shippingMethodId;


    public ShopOrderRequestDto() {
    }

    public Long getSiteUserId() {
        return siteUserId;
    }

    public void setSiteUserId(Long siteUserId) {
        this.siteUserId = siteUserId;
    }

    public Long getUserPaymentMethodId() {
        return userPaymentMethodId;
    }

    public void setUserPaymentMethodId(Long userPaymentMethodId) {
        this.userPaymentMethodId = userPaymentMethodId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(Long shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }
}
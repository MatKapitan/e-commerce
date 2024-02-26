package com.matkap.ecommerce.dto.requestDto.order;

public class ShopOrderRequestDto {

    private Long siteUserId;
    private Long userPaymentMethodId;
    private Long addressId;
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
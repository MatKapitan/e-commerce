package com.matkap.ecommerce.dto.requestDto.shoppingCard;

import jakarta.validation.constraints.NotNull;

public class ShoppingCardRequestDto {

    @NotNull(message = "siteUserId cannot be null")
    private Long siteUserId;
    @NotNull(message = "productItemId cannot be null")
    private Long productItemId;
    @NotNull(message = "quantity cannot be null")
    private Long quantity;


    public ShoppingCardRequestDto() {
    }

    public Long getSiteUserId() {
        return siteUserId;
    }

    public void setSiteUserId(Long siteUserId) {
        this.siteUserId = siteUserId;
    }

    public Long getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(Long productItemId) {
        this.productItemId = productItemId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}

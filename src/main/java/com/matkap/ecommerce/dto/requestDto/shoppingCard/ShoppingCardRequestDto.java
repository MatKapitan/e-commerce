package com.matkap.ecommerce.dto.requestDto.shoppingCard;

public class ShoppingCardRequestDto {

    private Long siteUserId;

    private Long productItemId;

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

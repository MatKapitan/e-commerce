package com.matkap.ecommerce.dto.requestDto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserReviewRequestDto {

    @NotNull(message = "siteUserId cannot be null")
    private Long siteUserId;
    @NotNull(message = "shopOrderId cannot be null")
    private Long shopOrderId;
    @NotNull(message = "ratingValue cannot be null")
    private Long ratingValue;
    @NotBlank(message = "comment cannot be blank")
    private String comment;




    public UserReviewRequestDto() {
    }

    public Long getSiteUserId() {
        return siteUserId;
    }

    public void setSiteUserId(Long siteUserId) {
        this.siteUserId = siteUserId;
    }

    public Long getShopOrderId() {
        return shopOrderId;
    }

    public void setShopOrderId(Long shopOrderId) {
        this.shopOrderId = shopOrderId;
    }

    public Long getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Long ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

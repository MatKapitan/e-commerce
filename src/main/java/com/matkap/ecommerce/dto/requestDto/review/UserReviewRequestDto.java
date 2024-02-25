package com.matkap.ecommerce.dto.requestDto.review;

public class UserReviewRequestDto {

    private Long siteUserId;
    private Long shopOrderId;
    private Long rating_value;
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

    public Long getRating_value() {
        return rating_value;
    }

    public void setRating_value(Long rating_value) {
        this.rating_value = rating_value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

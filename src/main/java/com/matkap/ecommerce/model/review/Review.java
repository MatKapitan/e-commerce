package com.matkap.ecommerce.model.review;

import com.matkap.ecommerce.model.order.ShopOrder;
import com.matkap.ecommerce.model.user.SiteUser;
import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "site_user_id", referencedColumnName = "id")
    private SiteUser siteUser;
    @ManyToOne
    @JoinColumn(name = "shop_order_id", referencedColumnName = "id")
    private ShopOrder shopOrder;
    @Column(name = "rating_value")
    private Long ratingValue;
    @Column(name = "comment")
    private String comment;

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }

    public ShopOrder getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(ShopOrder shopOrder) {
        this.shopOrder = shopOrder;
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

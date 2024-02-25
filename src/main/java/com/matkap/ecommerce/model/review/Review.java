package com.matkap.ecommerce.model.review;

import com.matkap.ecommerce.model.order.ShopOrder;
import com.matkap.ecommerce.model.user.SiteUser;
import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "site_user_id")
    private SiteUser siteUser;
    @ManyToOne
    @JoinColumn(name = "shop_order_id")
    private ShopOrder shopOrder;
    private Long rating_value;
    private String comment;



}

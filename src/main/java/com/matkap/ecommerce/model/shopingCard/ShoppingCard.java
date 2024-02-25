package com.matkap.ecommerce.model.shopingCard;

import com.matkap.ecommerce.model.product.ProductItem;
import com.matkap.ecommerce.model.user.SiteUser;
import jakarta.persistence.*;

@Entity
@Table(name = "Shopping_card")
public class ShoppingCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private SiteUser siteUser;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private ProductItem productItem;

    private Long quantity;

    public ShoppingCard() {
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

    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}

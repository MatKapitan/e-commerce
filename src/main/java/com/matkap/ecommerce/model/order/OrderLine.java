package com.matkap.ecommerce.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matkap.ecommerce.model.product.ProductItem;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "product_item_id")
    private ProductItem productItem;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "shop_order_id")
    private ShopOrder shopOrder;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "price")
    private BigDecimal price;

    public OrderLine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public ShopOrder getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(ShopOrder shopOrder) {
        this.shopOrder = shopOrder;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

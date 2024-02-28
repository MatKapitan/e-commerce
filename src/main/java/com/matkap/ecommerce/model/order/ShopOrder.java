package com.matkap.ecommerce.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matkap.ecommerce.model.payment.UserPaymentMethod;
import com.matkap.ecommerce.model.user.Address;
import com.matkap.ecommerce.model.user.SiteUser;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Entity
public class ShopOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "site_user_id")
    private SiteUser siteUser;
    private Timestamp order_date;
    @ManyToOne
    @JoinColumn(name = "user_payment_method_id")
    private UserPaymentMethod userPaymentMethod;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "shipping_method_id")
    private ShippingMethod shippingMethod;
    private BigDecimal orderTotal;
    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "shopOrder")
    private List<OrderLine> orderLines;



    public ShopOrder() {
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

    public Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    public UserPaymentMethod getUserPaymentMethod() {
        return userPaymentMethod;
    }

    public void setUserPaymentMethod(UserPaymentMethod userPaymentMethod) {
        this.userPaymentMethod = userPaymentMethod;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}

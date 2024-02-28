package com.matkap.ecommerce.model.payment;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matkap.ecommerce.model.user.SiteUser;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class UserPaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "site_user_id")
    private SiteUser siteUser;

    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;

    private String provider;
    private String accountNumber;
    private Timestamp expiryDate;
    private Boolean defaultPayment;


    public UserPaymentMethod() {
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getDefaultPayment() {
        return defaultPayment;
    }

    public void setDefaultPayment(Boolean defaultPayment) {
        this.defaultPayment = defaultPayment;
    }
}

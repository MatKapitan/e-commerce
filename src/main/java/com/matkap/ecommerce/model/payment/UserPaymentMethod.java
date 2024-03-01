package com.matkap.ecommerce.model.payment;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matkap.ecommerce.model.user.SiteUser;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "user_payment_method")
public class UserPaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "site_user_id", referencedColumnName = "id")
    private SiteUser siteUser;
    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "id")
    private PaymentType paymentType;
    @Column(name = "provider")
    private String provider;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "expiry_date")
    private Timestamp expiryDate;
    @Column(name = "default_payment")
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

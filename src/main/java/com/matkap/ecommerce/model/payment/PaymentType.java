package com.matkap.ecommerce.model.payment;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_type")
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "payment_values")
    private String paymentValues;

    public PaymentType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentValues() {
        return paymentValues;
    }

    public void setPaymentValues(String paymentValues) {
        this.paymentValues = paymentValues;
    }
}

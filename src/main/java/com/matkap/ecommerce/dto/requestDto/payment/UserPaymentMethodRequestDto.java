package com.matkap.ecommerce.dto.requestDto.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matkap.ecommerce.model.payment.PaymentType;
import com.matkap.ecommerce.model.user.SiteUser;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class UserPaymentMethodRequestDto {
    @NotNull(message = "siteUserId cannot be null")
    private Long siteUserId;
    @NotNull(message = "paymentTypeId cannot be null")
    private Long paymentTypeId;
    @NotBlank(message = "provider cannot be blank")
    private String provider;
    @NotBlank(message = "accountNumber cannot be blank")
    private String accountNumber;
    @NotNull(message = "expiryDate cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp expiryDate;

    public UserPaymentMethodRequestDto() {
    }

    public Long getSiteUserId() {
        return siteUserId;
    }

    public void setSiteUserId(Long siteUserId) {
        this.siteUserId = siteUserId;
    }

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
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

}

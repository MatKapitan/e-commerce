package com.matkap.ecommerce.dto.requestDto.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressRequestDto {

    @NotNull(message = "siteUserId cannot be null")
    private Long siteUserId;
    @NotBlank(message = "unitNumber cannot be blank")
    private String unitNumber;
    @NotBlank(message = "streetNumber cannot be blank")
    private String streetNumber;
    @NotBlank(message = "addressLine1 cannot be blank")
    private String addressLine1;
    private String addressLine2;
    @NotBlank(message = "city cannot be blank")
    private String city;
    @NotBlank(message = "region cannot be blank")
    private String region;
    @NotBlank(message = "postalCode cannot be blank")
    private String postalCode;
    @NotNull(message = "countryId cannot be null")
    private Long countryId;






    public AddressRequestDto() {
    }

    public Long getSiteUserId() {
        return siteUserId;
    }

    public void setSiteUserId(Long siteUserId) {
        this.siteUserId = siteUserId;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}

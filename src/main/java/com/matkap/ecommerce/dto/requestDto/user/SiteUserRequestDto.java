package com.matkap.ecommerce.dto.requestDto.user;

import jakarta.validation.constraints.NotBlank;

public class SiteUserRequestDto {

    @NotBlank(message = "emailAddress cannot be blank")
    private String emailAddress;
    @NotBlank(message = "phoneNumber cannot be blank")
    private String phoneNumber;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "username cannot be blank")
    private String username;


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

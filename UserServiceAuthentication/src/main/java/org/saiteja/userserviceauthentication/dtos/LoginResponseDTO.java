package org.saiteja.userserviceauthentication.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class LoginResponseDTO {
    private String tokenValue;
    private Date expiryAt;
    private String email;

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Date getExpiryAt() {
        return expiryAt;
    }

    public void setExpiryAt(Date expiryAt) {
        this.expiryAt = expiryAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
package org.saiteja.userserviceauthentication.dtos;

import lombok.Getter;
import lombok.Setter;


public class LogoutRequestDTO {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
package org.saiteja.userserviceauthentication.dtos;

import lombok.Getter;
import lombok.Setter;

public class SignupResponseDTO {
    private String name;
    private String email;
    private ResponseStatus responseStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}

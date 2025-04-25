package org.saiteja.userserviceauthentication.dtos;

import lombok.Getter;
import lombok.Setter;
import org.saiteja.userserviceauthentication.models.Role;
import org.saiteja.userserviceauthentication.models.User;

import java.util.List;


public class UserDTO {
    private String name;
    private String email;
    private List<Role> roles;
    private boolean isEmailVerified;

    public static UserDTO from(User user){
        if(user == null) return null;

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setRoles(user.getRoles());
        userDTO.setEmailVerified(user.isEmailVerified());

        return  userDTO;
    }

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }
}
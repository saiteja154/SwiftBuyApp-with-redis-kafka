package org.saiteja.userserviceauthentication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class Token extends BaseModel{
    private String value;

    @ManyToOne
    private User user;

    private Date expiryAt;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryAt() {
        return expiryAt;
    }

    public void setExpiryAt(Date expiryAt) {
        this.expiryAt = expiryAt;
    }
}

/*
when someone logs out -> invalidate the token =>

Token table => Value, user, expiry => Remove that row from the table?

There are 2 ways of removing rows from a table?
1. Actually removing the row from the table
2. Set the isDeleted column to true => Soft delete

After some days, i want to debug
 */

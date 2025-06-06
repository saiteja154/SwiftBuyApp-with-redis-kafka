package org.saiteja.userserviceauthentication.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean deleted; // initially false

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

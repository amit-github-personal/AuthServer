package com.example.springauthorizationserver.models;

import com.example.springauthorizationserver.util.SecurityUtil;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
* Resource Owner is a name given to each user registered
* on authorization server will be responsible for providing
* access to the service on the authorization server.
*
* @Author Amit Mishra
* */
@Entity
public class ResourceOwner extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public ResourceOwner(String username, String password, String...roles) {
        super(username, password, SecurityUtil.toGrantedAuthority(roles));
    }

    public ResourceOwner() {
        super(null, null, null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}

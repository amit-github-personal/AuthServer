package com.github.community.authserver.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Resource Owner is a name given to each user registered
 * on authorization server will be responsible for providing
 * access to the service on the authorization server.
 *
 * @Author Amit Mishra
 * */
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ResourceOwner {

    private long id;
    private String username;
    private String hashedPassword;
    private boolean isEnabled = true;
    private boolean locked = false;
    private boolean deleted=false;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> roles = new ArrayList<>();

}

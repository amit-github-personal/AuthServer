package com.github.community.authserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class RegisteredClients {

    private int id;
    private String client_id;
    private long client_id_issued_at;
    private String client_secret;
    private long client_secret_expires_at;
    private String client_name;
    private String client_authentication_methods;
    private String authorization_grant_types;
    private String redirect_uris;
    private String scopes;
    private String client_settings;
    private String token_settings;
    private int client_owner_id;

}

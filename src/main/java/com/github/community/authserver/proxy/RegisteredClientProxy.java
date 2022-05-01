package com.github.community.authserver.proxy;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

public interface RegisteredClientProxy {

    RegisteredClient saveRegisteredClient(RegisteredClient registeredClient);

    RegisteredClient updateRegisteredClient(RegisteredClient registeredClient);

    RegisteredClient findByClientId(String clientId);

    RegisteredClient findById(String registeredClientId);

    void deleteRegisteredClient(String clientId);

}

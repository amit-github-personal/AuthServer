package com.github.community.authserver.proxy.impl;

import com.github.community.authserver.proxy.RegisteredClientProxy;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

public class RegisteredClientProxyImpl implements RegisteredClientProxy {

    private RegisteredClientRepository registeredClientRepository;

    public RegisteredClientProxyImpl(RegisteredClientRepository registeredClientRepository) {
        this.registeredClientRepository = registeredClientRepository;
    }

    @Override
    public RegisteredClient saveRegisteredClient(RegisteredClient registeredClient) {
        registeredClientRepository.save(registeredClient);
        return findByClientId(registeredClient.getClientId());
    }

    @Override
    public RegisteredClient updateRegisteredClient(RegisteredClient registeredClient) {
        return saveRegisteredClient(registeredClient);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return registeredClientRepository.findByClientId(clientId);
    }

    @Override
    public RegisteredClient findById(String registeredClientId) {
        return registeredClientRepository.findById(registeredClientId);
    }

    @Override
    public void deleteRegisteredClient(String clientId) {

    }
}

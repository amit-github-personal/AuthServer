package com.example.springauthorizationserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientRegistration {

    @Autowired
    RegisteredClientRepository registeredClientRepository;

    @PostMapping
    RegisteredClient registerClient(@RequestBody RegisteredClient registeredClient) {
        registeredClientRepository.save(registeredClient);
        return registeredClientRepository.findByClientId(registeredClient.getClientId());
    }

}

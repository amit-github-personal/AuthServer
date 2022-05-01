package com.github.community.authserver.controllers.client;

import com.github.community.authserver.proxy.RegisteredClientProxy;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/client")
public class ClientRegistration {

    final
    RegisteredClientProxy registeredClientRepository;

    public ClientRegistration(RegisteredClientProxy registeredClientRepository) {
        this.registeredClientRepository = registeredClientRepository;
    }

    @PostMapping
    RegisteredClient registerClient(@RequestBody RegisteredClient registeredClient) {

        RegisteredClient client = RegisteredClient.withId(UUID.randomUUID().toString().substring(0, 8))
                .clientId(registeredClient.getClientId())
                .clientSecret("{noop}" + registeredClient.getClientSecret())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.JWT_BEARER)
                        .redirectUri((String)registeredClient.getRedirectUris().toArray()[0])
                                .scope("read").build();
        registeredClientRepository.saveRegisteredClient(client);
        return registeredClientRepository.findByClientId(client.getClientId());
    }

}

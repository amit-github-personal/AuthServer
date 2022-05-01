package com.github.community.authserver.service;

import com.github.community.authserver.models.ResourceOwner;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ResourceOwnerService extends UserDetailsService {

    ResourceOwner findByUsername(String username);

    ResourceOwner saveResourceOwner(ResourceOwner resourceOwner);

    ResourceOwner updateResourceOwner(ResourceOwner resourceOwner);

    void deleteResourceOwner(String username);
}

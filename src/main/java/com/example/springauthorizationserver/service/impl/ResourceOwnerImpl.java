package com.example.springauthorizationserver.service.impl;

import com.example.springauthorizationserver.models.ResourceOwner;
import com.example.springauthorizationserver.service.ResourceOwnerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ResourceOwnerImpl implements ResourceOwnerService {

    @Override
    public ResourceOwner findByUsername(String username) {
        return null;
    }

    @Override
    public ResourceOwner saveResourceOwner(ResourceOwner resourceOwner) {
        return null;
    }

    @Override
    public ResourceOwner updateResourceOwner(ResourceOwner resourceOwner) {
        return null;
    }

    @Override
    public void deleteResourceOwner(String username) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

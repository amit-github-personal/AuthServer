package com.example.springauthorizationserver.service.impl;

import com.example.springauthorizationserver.models.ResourceOwner;
import com.example.springauthorizationserver.service.ResourceOwnerService;
import com.example.springauthorizationserver.service.delegate.ResourceOwnerDelegate;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Setter
public class ResourceOwnerImpl implements ResourceOwnerService {

    private ResourceOwnerDelegate delegate;

    @Override
    public ResourceOwner findByUsername(String username) {
        return delegate.findByUsername(username);
    }

    @Override
    public ResourceOwner saveResourceOwner(ResourceOwner resourceOwner) {
        return delegate.saveResourceOwner(resourceOwner);
    }

    @Override
    public ResourceOwner updateResourceOwner(ResourceOwner resourceOwner) {
        return delegate.updateResourceOwner(resourceOwner);
    }

    @Override
    public void deleteResourceOwner(String username) {
        delegate.deleteResourceOwner(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return delegate.loadUserByUsername(username);
    }
}

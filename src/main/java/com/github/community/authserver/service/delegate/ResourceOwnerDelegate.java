package com.github.community.authserver.service.delegate;

import com.github.community.authserver.models.ResourceOwner;
import com.github.community.authserver.service.ResourceOwnerService;
import com.github.community.authserver.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

public class ResourceOwnerDelegate implements ResourceOwnerService {

    @Autowired
    EntityManager entityManager;

    private static final Logger log = LoggerFactory.getLogger(ResourceOwnerDelegate.class);

    @Override
    public ResourceOwner findByUsername(String username) {
        Optional<ResourceOwner> resourceOwner = entityManager
                .createQuery("select * from resource_owner where username=:username")
                .setParameter("username", username)
                .getResultStream().findAny();
        return resourceOwner.orElseThrow(() -> new NullPointerException("User doesn't exists."));
    }

    @Transactional
    @Override
    public ResourceOwner saveResourceOwner(ResourceOwner resourceOwner) {
        entityManager.persist(resourceOwner);
        return findByUsername(resourceOwner.getUsername());
    }

    @Override
    public ResourceOwner updateResourceOwner(ResourceOwner resourceOwner) {
        ResourceOwner existingResourceOwner = findById(resourceOwner.getId());
        if(existingResourceOwner == null ) {
            log.warn("Resource Owner with id {} doesn't exists. Going to save it.", resourceOwner.getId());
            return saveResourceOwner(resourceOwner);
        } else {
            //TODO Write Update Logic here.
            return null;
        }
    }

    @Override
    public void deleteResourceOwner(String username) {
        ResourceOwner existingResourceOwner = findByUsername(username);
        int affectedRows = entityManager.createQuery("update resource_owner set deleted=true where id=:resource_owner_id")
                .setParameter("resource_owner_id", existingResourceOwner.getId())
                .executeUpdate();
        if(affectedRows == 0) {
            log.error("Unable to delete the user with username {}", username);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResourceOwner user = findByUsername(username);
        if(user != null && user.isLocked() && !user.isEnabled()) {
            log.info("User with username {} ether locked or not enabled.", username);
            return null;
        }

        String [] grantedAuthorities = new String[user.getRoles().size()];
        user.getRoles().toArray(grantedAuthorities);
        User springUser = new User(user.getUsername(), user.getHashedPassword(), SecurityUtil.toGrantedAuthority(grantedAuthorities));
        return springUser;
    }

    private ResourceOwner findById(long resourceOwnerId) {
        return entityManager.find(ResourceOwner.class, resourceOwnerId);
    }
}

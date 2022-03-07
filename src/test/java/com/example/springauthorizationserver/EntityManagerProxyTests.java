package com.example.springauthorizationserver;

import com.example.springauthorizationserver.config.ApplicationSecurityConfig;
import com.example.springauthorizationserver.config.AuthorizationServerConfig;
import com.example.springauthorizationserver.proxy.SynchronizedEntityManager;
import com.example.springauthorizationserver.proxy.core.Proxies;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EntityManagerProxyTests {

    @Autowired
    EntityManager entityManager;

    @Test
    void initializedSynchronizedEntityManager() {
        assertNotNull(entityManager);
        SynchronizedEntityManager synchronizedEntityManager = Proxies.synchronizedEntityManagerProxy(SynchronizedEntityManager.class, entityManager);
        System.out.println(synchronizedEntityManager.getClass().getCanonicalName());
        assertNotNull(synchronizedEntityManager);
        System.out.println(synchronizedEntityManager.getMetamodel());
    }
}

package com.github.community.authserver;

import com.github.community.authserver.proxy.SynchronizedEntityManager;
import com.github.community.authserver.proxy.core.Proxies;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

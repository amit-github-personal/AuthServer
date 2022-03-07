package com.example.springauthorizationserver.proxy.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class SynchronizedEntityManagerInvocationHandler implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(SynchronizedEntityManagerInvocationHandler.class);

    private EntityManager entityManager;

    private HashMap<String, Method> methods = new HashMap<>();

    public SynchronizedEntityManagerInvocationHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
        for(Method method: entityManager.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        synchronized (entityManager) {
           try {
               log.info("Entering method {}", method.getName());
               return methods.get(method.getName()).invoke(entityManager, args);
           } catch(Exception e) {
               log.error("Exception occurred {}", e.getLocalizedMessage());
               throw e;
           } finally {
               log.info("Exiting method {}", method.getName());
           }
        }
    }
}

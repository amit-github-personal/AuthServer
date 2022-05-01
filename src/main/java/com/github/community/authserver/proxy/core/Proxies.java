package com.github.community.authserver.proxy.core;

import com.github.community.authserver.proxy.SynchronizedEntityManager;
import com.github.community.authserver.proxy.handlers.SynchronizedEntityManagerInvocationHandler;

import javax.persistence.EntityManager;
import java.lang.reflect.Proxy;

public class Proxies {

    /*
    * Creates a synchronized proxy around the any interface and proxy all
    * the method calls to the subject. the subject will have to have all
    * the methods reside in the interface.
    * @param intf The the interface that we want to proxy.
    * @param S the subject you want to proxy all the method calls.
    * */
    public static SynchronizedEntityManager synchronizedEntityManagerProxy(Class<?> intf, EntityManager subject) {
        return (SynchronizedEntityManager) Proxy
                .newProxyInstance(intf.getClassLoader(), new Class[]{intf},
                        new SynchronizedEntityManagerInvocationHandler(subject));
    }
}

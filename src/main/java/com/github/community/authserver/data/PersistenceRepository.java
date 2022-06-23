package com.github.community.authserver.data;

import java.beans.IntrospectionException;
import java.util.List;

/**
 * Interface to do basic curd operations on a database
 * for specific domain. The users can extend this interface
 * to have facility of performing basic curd operations that
 * has been already implemented using dynamic proxy.
 *
 * @param <T> The Domain class
 * @param <I> The identity of domain
 * @author Amit Mishra
 * @Since 0.1
 */
public interface PersistenceRepository<T, I> {

    T save(T object) throws IntrospectionException;

    T update(T object, I id);

    void delete(I id);

    List<T> getAll();
}

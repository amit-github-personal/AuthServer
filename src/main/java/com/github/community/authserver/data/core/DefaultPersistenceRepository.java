package com.github.community.authserver.data.core;

import com.github.community.authserver.data.PersistenceRepository;
import com.github.community.authserver.data.annotations.PrimaryKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.persistence.EntityManager;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;

public class DefaultPersistenceRepository<T, I> implements PersistenceRepository<T, I>, ApplicationContextAware {

    private final Logger LOG = LoggerFactory.getLogger (DefaultPersistenceRepository.class);
    private ApplicationContext context;
    private EntityManager entityManager;

    @Override
    public T save(T object) throws IntrospectionException {
        entityManager.persist (object);
        try {
            return find(object);
        } catch (Exception e) {
            throw new RuntimeException (e);
        }
    }

    private T find(T object) throws Exception {
        Field annotatedElement = getAnnotatedElement (object.getClass (), PrimaryKey.class);
        if(annotatedElement == null) throw new Exception ("Class " + object.getClass () + " doesn't declare any primary " +
                "key identifier @PrimaryKey. Please review your configuration and correct. ");
        annotatedElement.setAccessible (true);
        Long primaryKey = annotatedElement.getLong (object);
        return (T) entityManager.find (object.getClass (), primaryKey);
    }

    private T find(Class<?> entityClass, Long Id) throws Exception {
        return (T) entityManager.find (entityClass, Id);
    }

    private Field getAnnotatedElement(Class<?> lookupClass, Class<PrimaryKey> primaryKeyClass) {
        Field[] declaredFields = lookupClass.getDeclaredFields ();
        for (int i = 0; i < declaredFields.length; i++) {
            if(declaredFields[i].isAnnotationPresent (primaryKeyClass)) {
                return declaredFields[i];
            }
        }
        return null;
    }

    @Override
    public T update(T object, I id) {
        Field annotatedElement = getAnnotatedElement (object.getClass (), PrimaryKey.class);
        try {
            PropertyDescriptor pd = new PropertyDescriptor (annotatedElement.getName (), object.getClass ());
            Method writeMethod = pd.getWriteMethod ();
            Object invocationResult = writeMethod.invoke (object, Long.class.cast (id));
            return save ((T) invocationResult);
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException (e);
        }
    }

    @Override
    public void delete(I id) {
        Long primaryKeyIdentifier = Long.class.cast (id);
        Class<T> typeParameters = getTypeParameters (getClass (), 0);
        try {
            entityManager.remove (find (typeParameters, primaryKeyIdentifier));
        } catch (Exception e) {
            throw new RuntimeException (e);
        }
    }

    private Class<T> getTypeParameters(Class<? extends DefaultPersistenceRepository> aClass, int index) {
        return (Class<T>) ((ParameterizedType) aClass.getGenericSuperclass ()).getActualTypeArguments ()[index];
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        loadEntityManager();
    }

    private void loadEntityManager() {
        EntityManager bean = context.getBean (EntityManager.class);
        if(Objects.isNull (bean)) {
            LOG.error ("Failed to entity manager from application context");
        }
        this.entityManager = entityManager;
    }
}

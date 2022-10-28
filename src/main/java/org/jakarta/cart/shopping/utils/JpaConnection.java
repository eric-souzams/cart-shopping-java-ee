package org.jakarta.cart.shopping.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaConnection {

    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("connectionJpaHibernate");
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}

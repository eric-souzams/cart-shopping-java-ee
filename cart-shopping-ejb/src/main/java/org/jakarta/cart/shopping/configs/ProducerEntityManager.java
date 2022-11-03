package org.jakarta.cart.shopping.configs;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.logging.Logger;

@RequestScoped
public class ProducerEntityManager {

    @Inject
    private Logger log;

    @PersistenceContext(name = "connectionDB")
    private EntityManager entityManager;

    @Produces
    @RequestScoped
    private EntityManager entityManagerBean() {
        log.info("Creating connection with entity manager.");
        return entityManager;
    }

}

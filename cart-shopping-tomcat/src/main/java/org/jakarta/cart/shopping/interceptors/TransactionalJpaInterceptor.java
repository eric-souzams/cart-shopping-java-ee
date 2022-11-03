package org.jakarta.cart.shopping.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import org.jakarta.cart.shopping.exceptions.ServiceJdbcException;

import java.util.logging.Logger;

@TransactionJpa
@Interceptor
public class TransactionalJpaInterceptor {

    @Inject
    private Logger log;

    @Inject
    private EntityManager entityManager;

    @AroundInvoke
    public Object transactional(InvocationContext invocationContext) throws Exception {
        log.info("Opening Connection.");

        try {
            log.info("Starting transaction: calling method " + invocationContext.getMethod().getName() + " from " + invocationContext.getMethod().getDeclaringClass());

            entityManager.getTransaction().begin();
            Object result = invocationContext.proceed();
            entityManager.getTransaction().commit();

            log.info("Complete transaction: called method" + invocationContext.getMethod().getName() + " from " + invocationContext.getMethod().getDeclaringClass());

            return result;
        } catch (ServiceJdbcException exception) {
            entityManager.getTransaction().rollback();
            throw exception;
        }

    }
}

package org.jakarta.cart.shopping.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.jakarta.cart.shopping.exceptions.ServiceJdbcException;

import java.sql.Connection;
import java.util.logging.Logger;

@TransactionJdbc
@Interceptor
public class TransactionalJdbcInterceptor {

    @Inject
    private Logger log;

    @Inject
    private Connection connection;

    @AroundInvoke
    public Object transactional(InvocationContext invocationContext) throws Exception {
        log.info("Opening Connection.");
        if (connection.getAutoCommit()) connection.setAutoCommit(false);

        try {
            log.info("Starting transaction: calling method" + invocationContext.getMethod().getName() + " from " + invocationContext.getMethod().getDeclaringClass());

            Object result = invocationContext.proceed();

            connection.commit();

            log.info("Complete transaction: called method" + invocationContext.getMethod().getName() + " from " + invocationContext.getMethod().getDeclaringClass());

            return result;
        } catch (ServiceJdbcException exception) {
            connection.rollback();
            throw exception;
        }

    }
}

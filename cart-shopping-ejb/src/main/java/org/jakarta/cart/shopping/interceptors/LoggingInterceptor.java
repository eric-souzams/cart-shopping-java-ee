package org.jakarta.cart.shopping.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {

    @Inject
    private Logger log;

    @AroundInvoke
    public Object logging(InvocationContext invocationContext) throws Exception {
        log.info("Before call method: " + invocationContext.getMethod().getDeclaringClass());
        Object result = invocationContext.proceed();
        log.info("After call method: " + invocationContext.getMethod().getName());

        return result;
    }

}

package org.jakarta.cart.shopping.annotations;

import jakarta.inject.Qualifier;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface ProductServicePrincipal {
}

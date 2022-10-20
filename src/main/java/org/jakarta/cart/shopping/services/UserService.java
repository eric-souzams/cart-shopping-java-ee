package org.jakarta.cart.shopping.services;

import org.jakarta.cart.shopping.models.User;

import java.util.Optional;

public interface UserService {

    Optional<User> login(String username, String password);

}

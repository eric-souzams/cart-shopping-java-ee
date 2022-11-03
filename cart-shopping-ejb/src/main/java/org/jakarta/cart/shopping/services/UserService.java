package org.jakarta.cart.shopping.services;

import jakarta.ejb.Local;
import org.jakarta.cart.shopping.models.entities.User;

import java.util.Optional;

@Local
public interface UserService {

    Optional<User> login(String username, String password);

}

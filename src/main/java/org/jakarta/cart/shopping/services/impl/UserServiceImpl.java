package org.jakarta.cart.shopping.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jakarta.cart.shopping.annotations.Service;
import org.jakarta.cart.shopping.exceptions.ServiceJdbcException;
import org.jakarta.cart.shopping.interceptors.Logging;
import org.jakarta.cart.shopping.models.User;
import org.jakarta.cart.shopping.repositories.UserRepository;
import org.jakarta.cart.shopping.repositories.UserRepositoryJdbcImpl;
import org.jakarta.cart.shopping.services.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public Optional<User> login(String username, String password) {
        try {
            return Optional.ofNullable(userRepository.findByUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

}

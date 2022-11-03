package org.jakarta.cart.shopping.services.impl;

import jakarta.inject.Inject;
import org.jakarta.cart.shopping.annotations.JpaRepository;
import org.jakarta.cart.shopping.annotations.Service;
import org.jakarta.cart.shopping.exceptions.ServiceJdbcException;
import org.jakarta.cart.shopping.interceptors.TransactionJpa;
import org.jakarta.cart.shopping.models.entities.User;
import org.jakarta.cart.shopping.repositories.UserRepository;
import org.jakarta.cart.shopping.services.UserService;

import java.util.Optional;

@Service
@TransactionJpa
public class UserServiceImpl implements UserService {

    @Inject
    @JpaRepository
    private UserRepository userRepository;

    @Override
    public Optional<User> login(String username, String password) {
        try {
            return Optional.ofNullable(userRepository.findByUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

}

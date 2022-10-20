package org.jakarta.cart.shopping.repositories;

import org.jakarta.cart.shopping.models.User;

import java.sql.SQLException;

public interface UserRepository extends Repository<User> {

    User findByUsername(String username) throws SQLException;

}

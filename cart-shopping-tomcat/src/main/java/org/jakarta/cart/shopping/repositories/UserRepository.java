package org.jakarta.cart.shopping.repositories;

import org.jakarta.cart.shopping.models.entities.User;

public interface UserRepository extends CrudRepository<User> {

    User findByUsername(String username) throws Exception;

}

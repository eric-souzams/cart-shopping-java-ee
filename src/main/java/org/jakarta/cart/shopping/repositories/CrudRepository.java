package org.jakarta.cart.shopping.repositories;

import java.util.List;

public interface CrudRepository<T> {

    List<T> listAll() throws Exception;

    T findById(Long id) throws Exception;

    void save(T t) throws Exception;

    void delete(Long id) throws Exception;
}

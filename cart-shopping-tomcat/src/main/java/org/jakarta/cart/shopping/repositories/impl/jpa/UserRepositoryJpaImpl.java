package org.jakarta.cart.shopping.repositories.impl.jpa;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jakarta.cart.shopping.annotations.JpaRepository;
import org.jakarta.cart.shopping.annotations.Repository;
import org.jakarta.cart.shopping.models.entities.User;
import org.jakarta.cart.shopping.repositories.UserRepository;

import java.util.List;
import java.util.logging.Logger;

@JpaRepository
@Repository
public class UserRepositoryJpaImpl implements UserRepository {

    @Inject
    private EntityManager entityManager;

    @Inject
    private Logger log;

    @Override
    public List<User> listAll() throws Exception {
       return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User findById(Long id) throws Exception {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) throws Exception {
        if (user.getId() != null && user.getId() > 0) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        User user = findById(id);
        entityManager.remove(user);
    }

    @Override
    public User findByUsername(String username) throws Exception {
        return entityManager.createQuery("select u from User u where u.username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}

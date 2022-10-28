package org.jakarta.cart.shopping.repositories.impl.jpa;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jakarta.cart.shopping.annotations.JpaRepository;
import org.jakarta.cart.shopping.annotations.Repository;
import org.jakarta.cart.shopping.models.entities.Product;
import org.jakarta.cart.shopping.repositories.CrudRepository;

import java.util.List;
import java.util.logging.Logger;

@JpaRepository
@Repository
public class ProductRepositoryJpaImpl implements CrudRepository<Product> {

    @Inject
    private EntityManager entityManager;

    @Inject
    private Logger log;

    @PostConstruct
    public void init() {
        log.info("Creating a bean: " + this.getClass().getName());
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying a bean:" + this.getClass().getName());
    }

    @Override
    public List<Product> listAll() throws Exception {
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product findById(Long id) throws Exception {
        return entityManager.find(Product.class, id);
    }

    @Override
    public void save(Product product) throws Exception {
        if (product.getId() != null && product.getId() > 0) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Product product = findById(id);
        entityManager.remove(product);
    }
}

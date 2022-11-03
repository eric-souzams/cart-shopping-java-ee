package org.jakarta.cart.shopping.repositories.impl.jpa;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jakarta.cart.shopping.annotations.JpaRepository;
import org.jakarta.cart.shopping.annotations.Repository;
import org.jakarta.cart.shopping.models.entities.Category;
import org.jakarta.cart.shopping.repositories.CrudRepository;

import java.util.List;
import java.util.logging.Logger;

@JpaRepository
@Repository
public class CategoryRepositoryJpaImpl implements CrudRepository<Category> {

    @Inject
    private EntityManager entityManager;

    @Inject
    private Logger log;

    @Override
    public List<Category> listAll() throws Exception {
        return entityManager.createQuery("from Category", Category.class).getResultList();
    }

    @Override
    public Category findById(Long id) throws Exception {
        return entityManager.find(Category.class, id);
    }

    @Override
    public void save(Category category) throws Exception {
        if (category.getId() != null && category.getId() > 0) {
            entityManager.persist(category);
        } else {
            entityManager.merge(category);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Category category = findById(id);
        entityManager.remove(category);
    }
}

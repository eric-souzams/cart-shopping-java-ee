package org.jakarta.cart.shopping.services.impl;

import jakarta.inject.Inject;
import org.jakarta.cart.shopping.annotations.JpaRepository;
import org.jakarta.cart.shopping.annotations.ProductServicePrincipal;
import org.jakarta.cart.shopping.annotations.Service;
import org.jakarta.cart.shopping.exceptions.ServiceJdbcException;
import org.jakarta.cart.shopping.interceptors.TransactionJpa;
import org.jakarta.cart.shopping.models.entities.Category;
import org.jakarta.cart.shopping.models.entities.Product;
import org.jakarta.cart.shopping.repositories.CrudRepository;
import org.jakarta.cart.shopping.services.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@ProductServicePrincipal
@TransactionJpa
public class ProductServiceImpl implements ProductService {

    @Inject
    @JpaRepository
    private CrudRepository<Product> productCrudRepository;

    @Inject
    @JpaRepository
    private CrudRepository<Category> categoryCrudRepository;

    // Product
    @Override
    public List<Product> list() {
        try {
            return productCrudRepository.listAll();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            return Optional.ofNullable(productCrudRepository.findById(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    @Override
    public void save(Product product) {
        try {
            productCrudRepository.save(product);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            productCrudRepository.delete(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    // Category
    @Override
    public List<Category> listCategories() {
        try {
            return categoryCrudRepository.listAll();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        try {
            return Optional.ofNullable(categoryCrudRepository.findById(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    @Override
    public void saveCategory(Category category) {
        try {
            categoryCrudRepository.save(category);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    @Override
    public void deleteCategory(Long id) {
        try {
            categoryCrudRepository.delete(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }
}

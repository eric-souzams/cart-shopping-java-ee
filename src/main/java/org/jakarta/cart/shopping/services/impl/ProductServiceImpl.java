package org.jakarta.cart.shopping.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jakarta.cart.shopping.annotations.ProductServicePrincipal;
import org.jakarta.cart.shopping.annotations.Service;
import org.jakarta.cart.shopping.exceptions.ServiceJdbcException;
import org.jakarta.cart.shopping.interceptors.Logging;
import org.jakarta.cart.shopping.models.Category;
import org.jakarta.cart.shopping.models.Product;
import org.jakarta.cart.shopping.repositories.Repository;
import org.jakarta.cart.shopping.services.ProductService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@ProductServicePrincipal
public class ProductServiceImpl implements ProductService {

    @Inject
    private Repository<Product> productRepository;

    @Inject
    private Repository<Category> categoryRepository;

    // Product
    @Override
    public List<Product> list() {
        try {
            return productRepository.listAll();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            return Optional.ofNullable(productRepository.findById(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    @Override
    public void save(Product product) {
        try {
            productRepository.save(product);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            productRepository.delete(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    // Category
    @Override
    public List<Category> listCategories() {
        try {
            return categoryRepository.listAll();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        try {
            return Optional.ofNullable(categoryRepository.findById(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    @Override
    public void saveCategory(Category category) {
        try {
            categoryRepository.save(category);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }

    @Override
    public void deleteCategory(Long id) {
        try {
            categoryRepository.delete(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage());
        }
    }
}

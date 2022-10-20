package org.jakarta.cart.shopping.services.impl;

import org.jakarta.cart.shopping.exceptions.ServiceJdbcException;
import org.jakarta.cart.shopping.models.Category;
import org.jakarta.cart.shopping.models.Product;
import org.jakarta.cart.shopping.repositories.CategoryRepositoryJdbcImpl;
import org.jakarta.cart.shopping.repositories.ProductRepositoryJdbcImpl;
import org.jakarta.cart.shopping.repositories.Repository;
import org.jakarta.cart.shopping.services.ProductService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductServiceJdbcImpl implements ProductService {

    private final Repository<Product> productRepository;
    private final Repository<Category> categoryRepository;

    // Product
    public ProductServiceJdbcImpl(Connection connection) {
        this.productRepository = new ProductRepositoryJdbcImpl(connection);
        this.categoryRepository = new CategoryRepositoryJdbcImpl(connection);
    }

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

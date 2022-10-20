package org.jakarta.cart.shopping.services.impl;


import org.jakarta.cart.shopping.models.Category;
import org.jakarta.cart.shopping.models.Product;
import org.jakarta.cart.shopping.services.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> list() {
        return Arrays.asList(
                new Product(1L,"notebook", "pc", 5000),
                new Product(2L,"smartphone", "cell", 300),
                new Product(3L,"smart-tv", "tv", 200)
        );
    }

    @Override
    public Optional<Product> findById(Long id) {
        return list().stream().filter(p -> p.getId().equals(id)).findAny();
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Category> listCategories() {
        return null;
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return Optional.empty();
    }

    @Override
    public void saveCategory(Category category) {

    }

    @Override
    public void deleteCategory(Long id) {

    }
}

package org.jakarta.cart.shopping.services;

import org.jakarta.cart.shopping.models.entities.Category;
import org.jakarta.cart.shopping.models.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> list();
    Optional<Product> findById(Long id);
    void save(Product product);
    void delete(Long id);

    List<Category> listCategories();
    Optional<Category> findCategoryById(Long id);
    void saveCategory(Category category);
    void deleteCategory(Long id);

}

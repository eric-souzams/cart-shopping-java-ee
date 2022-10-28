package org.jakarta.cart.shopping.repositories.impl.jdbc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jakarta.cart.shopping.annotations.JdbcRepository;
import org.jakarta.cart.shopping.annotations.Repository;
import org.jakarta.cart.shopping.models.entities.Category;
import org.jakarta.cart.shopping.repositories.CrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@JdbcRepository
@Repository
public class CategoryRepositoryJdbcImpl implements CrudRepository<Category> {

    @Inject
    @Named("conn")
    private Connection connection;

    @Override
    public List<Category> listAll() throws SQLException {
        List<Category> categories = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM category");

            while (resultSet.next()) {
                Category category = getCategory(resultSet);
                categories.add(category);
            }
        }

        return categories;
    }

    @Override
    public Category findById(Long id) throws SQLException {
        Category category = null;
        String query = "SELECT * FROM category WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    category = getCategory(resultSet);
                }
            }
        }

        return category;
    }

    @Override
    public void save(Category category) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    private static Category getCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("id"));
        category.setName(resultSet.getString("name"));
        return category;
    }
}

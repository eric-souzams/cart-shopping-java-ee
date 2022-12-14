package org.jakarta.cart.shopping.repositories.impl.jdbc;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jakarta.cart.shopping.annotations.JdbcRepository;
import org.jakarta.cart.shopping.annotations.Repository;
import org.jakarta.cart.shopping.models.entities.Category;
import org.jakarta.cart.shopping.models.entities.Product;
import org.jakarta.cart.shopping.repositories.CrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@JdbcRepository
@Repository
public class ProductRepositoryJdbcImpl implements CrudRepository<Product> {

    @Inject
    @Named("conn")
    private Connection connection;

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
    public List<Product> listAll() throws SQLException {
        List<Product> products = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT p.*, c.name as category FROM product as p " +
                    " inner join category as c ON (p.category_id = c.id) ORDER BY p.id ASC");

            while (resultSet.next()) {
                Product product = getProduct(resultSet);

                products.add(product);
            }
        }

        return products;
    }

    @Override
    public Product findById(Long id) throws SQLException {
        Product product = null;

        String query = "SELECT p.*, c.name as category FROM product as p " +
                 "inner join category as c ON (p.category_id = c.id) WHERE p.id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = getProduct(resultSet);
                }
            }
        }

        return product;
    }

    @Override
    public void save(Product product) throws SQLException {
        String sql;

        if (product.getId() != null && product.getId() > 0) {
            sql = "UPDATE product SET name = ?, price = ?, sku = ?, category_id = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO product (name, price, sku, category_id, created_at) VALUES (?,?,?,?,?)";
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setString(3, product.getSku());
            statement.setLong(4, product.getCategory().getId());

            if (product.getId() != null && product.getId() > 0) {
                statement.setLong(5, product.getId());
            } else {
                statement.setDate(5, Date.valueOf(product.getCreatedAt()));
            }

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM product WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        }
    }

    private static Product getProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getInt("price"));
        product.setSku(resultSet.getString("sku"));
        product.setCreatedAt(resultSet.getDate("created_at").toLocalDate());

        Category category = new Category();
        category.setName(resultSet.getString("category"));
        category.setId(resultSet.getLong("category_id"));
        product.setCategory(category);

        return product;
    }
}

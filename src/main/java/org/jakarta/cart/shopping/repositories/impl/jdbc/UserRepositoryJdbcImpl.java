package org.jakarta.cart.shopping.repositories.impl.jdbc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jakarta.cart.shopping.annotations.JdbcRepository;
import org.jakarta.cart.shopping.annotations.Repository;
import org.jakarta.cart.shopping.models.entities.User;
import org.jakarta.cart.shopping.repositories.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@JdbcRepository
@Repository
public class UserRepositoryJdbcImpl implements UserRepository {

    @Inject
    @Named("conn")
    private Connection connection;

    @Override
    public List<User> listAll() throws SQLException {
        List<User> users = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                User user = getUser(resultSet);
                users.add(user);
            }
        }

        return users;
    }

    @Override
    public User findById(Long id) throws SQLException {
        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = getUser(resultSet);
                }
            }
        }

        return user;
    }

    @Override
    public void save(User user) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        }
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        User user = null;
        String query = "SELECT * FROM users WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = getUser(resultSet);
                }
            }
        }

        return user;
    }

    private static User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }
}

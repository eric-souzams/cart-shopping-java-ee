package org.jakarta.cart.shopping.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private final static String url = "jdbc:mysql://localhost:3306/javaee_cart";
    private final static String username = "root";
    private final static String password = "root";

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(url, username, password);
    }

}

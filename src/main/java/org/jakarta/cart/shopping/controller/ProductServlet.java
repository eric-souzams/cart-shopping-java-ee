package org.jakarta.cart.shopping.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jakarta.cart.shopping.models.Product;
import org.jakarta.cart.shopping.services.*;
import org.jakarta.cart.shopping.services.impl.LoginServiceImpl;
import org.jakarta.cart.shopping.services.impl.ProductServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("conn");

        ProductService productService = new ProductServiceJdbcImpl(connection);
        List<Product> products = productService.list();

        LoginService loginService = new LoginServiceImpl();
        Optional<String> username = loginService.getUsername(req);

        req.setAttribute("products", products);
        req.setAttribute("username", username);
        req.setAttribute("title", "Products List");

        getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}

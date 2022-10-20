package org.jakarta.cart.shopping.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jakarta.cart.shopping.annotations.ProductServicePrincipal;
import org.jakarta.cart.shopping.models.Product;
import org.jakarta.cart.shopping.services.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @Inject
    @ProductServicePrincipal
    private ProductService productService;

    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.list();
        Optional<String> username = loginService.getUsername(req);

        req.setAttribute("products", products);
        req.setAttribute("username", username);
        req.setAttribute("title", "Products List");

        getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}

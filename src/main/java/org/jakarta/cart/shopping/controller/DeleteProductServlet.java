package org.jakarta.cart.shopping.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jakarta.cart.shopping.models.Product;
import org.jakarta.cart.shopping.services.ProductService;
import org.jakarta.cart.shopping.services.impl.ProductServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/products/delete")
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("conn");
        ProductService productService = new ProductServiceJdbcImpl(connection);

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if (id > 0) {
            Optional<Product> foundProduct = productService.findById(id);
            if (foundProduct.isPresent()) {
                productService.delete(id);
                resp.sendRedirect(req.getContextPath() + "/products");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found.");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid product id.");
        }
    }
}

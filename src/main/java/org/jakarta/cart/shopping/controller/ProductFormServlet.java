package org.jakarta.cart.shopping.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jakarta.cart.shopping.models.Category;
import org.jakarta.cart.shopping.models.Product;
import org.jakarta.cart.shopping.services.ProductService;
import org.jakarta.cart.shopping.services.impl.ProductServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet(value = "/products/form")
public class ProductFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("conn");
        ProductService productService = new ProductServiceJdbcImpl(connection);

        long productId;
        try {
            productId = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            productId = 0L;
        }

        Product product = new Product();
        product.setCategory(new Category());

        if (productId > 0) {
            Optional<Product> productFound = productService.findById(productId);
            if (productFound.isPresent()) {
                product = productFound.get();
            }
        }

        req.setAttribute("categories", productService.listCategories());
        req.setAttribute("product", product);
        req.setAttribute("title", "Form Product");

        getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("conn");
        ProductService productService = new ProductServiceJdbcImpl(connection);
        Map<String, String> errors = new HashMap<>();

        String name = req.getParameter("name");
        String sku = req.getParameter("sku");
        String createdAtString = req.getParameter("created_at");

        Integer price;
        try {
            price = Integer.valueOf(req.getParameter("price"));
        } catch (NumberFormatException e) {
            price = 0;
        }

        Long categoryId;
        try {
            categoryId = Long.valueOf(req.getParameter("category"));
        } catch (NumberFormatException e) {
            categoryId = 0L;
        }

        if (name == null || name.isBlank()) errors.put("name", "Name is required.");
        if (sku == null || sku.isBlank()) errors.put("sku", "Sku is required.");
        if (createdAtString.isBlank()) errors.put("created_at", "Register date is required.");
        if (price.equals(0)) errors.put("price", "Price is required.");
        if (categoryId.equals(0L)) errors.put("category", "Category is required.");

        LocalDate createdAt;
        try {
            createdAt= LocalDate.parse(createdAtString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            createdAt = null;
        }

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setSku(sku);
        product.setCreatedAt(createdAt);

        Category category = new Category();
        category.setId(categoryId);
        product.setCategory(category);

        if (errors.isEmpty()) {
            productService.save(product);
            resp.sendRedirect(req.getContextPath() + "/products");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("categories", productService.listCategories());
            req.setAttribute("product", product);
            req.setAttribute("title", "Form Product");

            getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
        }
    }
}

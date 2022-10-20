package org.jakarta.cart.shopping.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.jakarta.cart.shopping.models.User;
import org.jakarta.cart.shopping.services.UserService;
import org.jakarta.cart.shopping.services.impl.UserServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Login");
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<User> credentials = userService.login(username, password);

        if (credentials.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
        }

        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}

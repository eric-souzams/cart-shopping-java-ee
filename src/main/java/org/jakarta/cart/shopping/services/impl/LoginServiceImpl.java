package org.jakarta.cart.shopping.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.jakarta.cart.shopping.annotations.Service;
import org.jakarta.cart.shopping.interceptors.Logging;
import org.jakarta.cart.shopping.services.LoginService;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return Optional.of(username);
        }

        return Optional.empty();
    }

}

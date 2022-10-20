package org.jakarta.cart.shopping.filters;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jakarta.cart.shopping.services.LoginService;
import org.jakarta.cart.shopping.services.impl.LoginServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/cart/*", "/add-cart", "/products/form/*", "/products/delete/*"})
public class LoginFilter implements Filter {

    @Inject
    private LoginService loginService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Optional<String> username = loginService.getUsername((HttpServletRequest) request);

        if (username.isPresent()) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "You haven't permission to access this content.");
        }
    }
}

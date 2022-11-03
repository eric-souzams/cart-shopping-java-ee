package org.jakarta.cart.shopping.filters;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.jakarta.cart.shopping.exceptions.ServiceJdbcException;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebFilter("/*")
public class ConnectionFilter implements Filter {

//    @Inject
//    @Named("conn")
//    private Connection conn;

    @Inject
    private Logger log;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        try {
//            Connection connRequest = this.conn;
//            log.info("Opening Connection.");
//
//            if (connRequest.getAutoCommit()) connRequest.setAutoCommit(false);

            try {
//                request.setAttribute("conn", connRequest);
                chain.doFilter(request, response);
//                connRequest.commit();
            } catch (ServiceJdbcException e) {
//                connRequest.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }

//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}

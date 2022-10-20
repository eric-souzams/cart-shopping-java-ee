package org.jakarta.cart.shopping.listenner;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class ApplicationListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Starting application.");
        servletContext = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destroying application.");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Starting request.");
        sre.getServletRequest().setAttribute("title", "Cart Servlet");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Destroying request.");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Starting session http.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destroying session http.");
    }
}

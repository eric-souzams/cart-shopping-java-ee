package org.jakarta.cart.shopping.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@ApplicationScoped
public class ProducerResources {

    @Inject
    private Logger log;

    @Resource(lookup = "java:/MySqlDS")
    private DataSource dataSource;

    @Produces
    @RequestScoped
    @Named("conn")
    private Connection connectionBean() throws NamingException, SQLException {
//        Context initContext = new InitialContext();
//        Context envContext  = (Context)initContext.lookup("java:/comp/env");
//        DataSource ds = (DataSource)envContext.lookup("jdbc/mysqlDB");
        return dataSource.getConnection();
    }

    @Produces
    private Logger LoggerBean(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    public void close(@Disposes Connection connection) throws SQLException {
        connection.close();
        log.info("Closing Connection.");
    }

}

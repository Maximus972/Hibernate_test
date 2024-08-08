package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.*;
import java.util.Properties;

public class Util {
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private Driver driver;
    private Connection connection;
    private SessionFactory sessionFactory;


    public Util() throws SQLException {
        URL = "jdbc:mysql://localhost:3306/sys";
        USER = "root";
        PASSWORD = "admin";
        driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/sys");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "admin");
        this.sessionFactory = new Configuration()
                .setProperties(properties).addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public Connection getConnection() {
        return connection;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

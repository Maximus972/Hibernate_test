package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private Driver driver;
    private Connection connection;

    public Util() throws SQLException {
        URL = "jdbc:mysql://localhost:3306/sys";
        USER = "root";
        PASSWORD = "admin";
        driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Util(String URL, String USER, String PASSWORD) throws SQLException {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }
}

package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private Driver driver = null;
    private Connection connection = null;
    private Statement statement = null;
    private String tableName = null;

    public Util() throws SQLException {
        URL = "jdbc:mysql://localhost:3306/sys";
        USER = "root";
        PASSWORD = "admin";
        driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        this.statement = connection.createStatement();
    }

    public Util(String URL, String USER, String PASSWORD) throws SQLException {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        this.statement = connection.createStatement();
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public String getTableName() {
        return tableName;
    }
}

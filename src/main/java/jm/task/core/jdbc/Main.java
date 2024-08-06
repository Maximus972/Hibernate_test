package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    private static final String INSERT_NEW = "insert into sys values(?,?,?,?)";

    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("John", "Travolta", (byte) 5);
        userService.saveUser("Jane", "Carter", (byte) 24);
        userService.saveUser("John", "Conor", (byte) 32);
        userService.saveUser("Jane", "Bob", (byte) 45);
        List<User> users = userService.getAllUsers();
        users.stream().forEach(t -> System.out.println(t.toString()));
        userService.dropUsersTable();
    }
}

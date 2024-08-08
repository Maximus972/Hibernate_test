package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("John", "Travolta", (byte) 5);
        userService.saveUser("Jane", "Carter", (byte) 24);
        userService.saveUser("John", "Conor", (byte) 32);
        userService.saveUser("Jane", "Bob", (byte) 45);
        List<User> users = userService.getAllUsers();
        users.forEach(t -> System.out.println(t.toString()));
        userService.dropUsersTable();
    }
}

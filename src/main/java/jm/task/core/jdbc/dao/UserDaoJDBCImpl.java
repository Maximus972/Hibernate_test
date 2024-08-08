package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {
        try {
            connection = new Util().getConnection();
        } catch (SQLException e) {
            System.out.println("Ошибка при установке соединения");
        }
    }

    public void createUsersTable() {
        try {
            connection.createStatement().execute("CREATE TABLE users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), lastName VARCHAR(50), age INT)");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы");
        }
    }

    public void dropUsersTable() {
        try {
            connection.createStatement().execute("DROP TABLE users");
        } catch (SQLException e) {
            System.out.println("шибка при удалении таблицы");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT users (name, lastName, age) VALUES (?,?,?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.execute();
            System.out.println("User " + name + " добавлен в базу данных");
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении пользователя");
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка во время удаления пользователя из таблицы.");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при выводе данных");
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            connection.createStatement().execute("DELETE FROM users");
        } catch (SQLException e) {
            System.out.println("Ошибка при очищении таблицы");
        }
    }
}

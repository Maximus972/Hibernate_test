package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        try {
            this.sessionFactory = new Util().getSessionFactory();
        } catch (SQLException e) {
            System.out.println("Ошибка при получении SessionFactory");
        }
    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sqlQuery = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), lastName VARCHAR(50), age INT)";
            Query query = session.createSQLQuery(sqlQuery).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch(HibernateException e) {
            System.out.println("Ошибка при создании таблицы");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sqlQuery = "DROP TABLE IF EXISTS users";
            Query query = session.createSQLQuery(sqlQuery).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch(HibernateException e) {
            System.out.println("Ошибка при удалении таблицы");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User " + name + " добавлен в базу данных");
        } catch(HibernateException e) {
            System.out.println("Ошибка при добавлении user");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        }
        catch(HibernateException e) {
            System.out.println("Ошибка при удалении user");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = null;
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            result = query.list();
            return result;
        } catch(HibernateException e) {
            System.out.println("Ошибка при получении данных");
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sqlQuery = "DELETE FROM users";
            Query query = session.createSQLQuery(sqlQuery).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch(HibernateException e) {
            System.out.println("Ошибка при очищении таблицы");
        }
    }
}

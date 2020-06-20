package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {

    private SessionFactory sessionFactory;

    public UserServiceImpl() {
        this.sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        getUserDaoHibernate().createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        getUserDaoHibernate().dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        getUserDaoHibernate().saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        getUserDaoHibernate().removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return getUserDaoHibernate().getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        getUserDaoHibernate().cleanUsersTable();
    }

    private static UserDaoHibernateImpl getUserDaoHibernate() {
        return new UserDaoHibernateImpl(Util.getSessionFactory().openSession());
    }
}

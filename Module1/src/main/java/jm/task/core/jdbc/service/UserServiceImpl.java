package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public void createUsersTable() {
        getUserDaoJDBC().createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        getUserDaoJDBC().dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        getUserDaoJDBC().saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        getUserDaoJDBC().removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return getUserDaoJDBC().getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        getUserDaoJDBC().cleanUsersTable();
    }

    private static UserDaoJDBCImpl getUserDaoJDBC() {
        return new UserDaoJDBCImpl(Util.getMysqlConnection());
    }
}

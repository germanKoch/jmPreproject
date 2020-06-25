package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    User getUserById(long id);

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(long id);

    void changeUser(User user);

    User getUserByName(String name);

}

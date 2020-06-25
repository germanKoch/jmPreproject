package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    User getUserById(long id);

    List<User> getAllUsers();

    boolean saveUser(User user);

    boolean deleteUser(long id);

    void changeUser(User user);
}

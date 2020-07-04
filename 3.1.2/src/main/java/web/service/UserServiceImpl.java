package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.Role;
import web.model.User;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User getUserById(long id){
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public boolean saveUser(User user) {
        User userFromDB = userDAO.getUserByName(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        userDAO.addUser(user);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteUser(long userId) {
        if (userDAO.getUserById(userId) != null) {
            userDAO.deleteUser(userId);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean changeUser(User user) {
        User userFromDB = userDAO.getUserByName(user.getUsername());

        if (userFromDB != null && user.getId().equals(userFromDB.getId())) {
            return false;
        }

        userDAO.changeUser(user);
        return true;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) {
        User user = userDAO.getUserByName(name);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}

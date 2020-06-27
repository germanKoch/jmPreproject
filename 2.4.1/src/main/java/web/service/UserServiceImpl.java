package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.Role;
import web.model.User;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Override
    public User getUserById(long id){
        return userDAO.getUserById(id);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    @Override
    public boolean saveUser(User user) {
        User userFromDB = userDAO.getUserByName(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(user.getPassword());
        userDAO.addUser(user);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteUser(long userId) {
        if (userDAO.getUserById(userId) != null) {
            userDAO.deleteUser(userId);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void changeUser(User user) {
        userDAO.changeUser(user);
    }

    @Transactional

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = userDAO.getUserByName(name);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}

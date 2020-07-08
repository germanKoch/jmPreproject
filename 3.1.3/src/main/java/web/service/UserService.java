package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.Role;
import web.model.User;

import java.util.Collections;
import java.util.List;

public interface UserService extends UserDetailsService {

    public User getUserById(long id);

    public List<User> getAllUsers();

    public boolean saveUser(User user);

    public boolean deleteUser(long userId);

    public boolean changeUser(User user);

    public User getUserByName(String name);

    @Override
    public UserDetails loadUserByUsername(String name);


}

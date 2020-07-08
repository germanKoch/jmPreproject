package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/ex")
    public String getEx() {
        return "hz";
    }

    @GetMapping("/all")
    public List<User> getUserList() {
        return userService.getAllUsers();
    }

    @PostMapping("/delete")
    public long deleteUser(@RequestParam long id) {
        if (userService.deleteUser(id)) {
            return id;
        } else {
            throw new RuntimeException("error");
        }
    }

    //todo: getStringROles -- very hrenovo

    @PostMapping("/add")
    public User addUser(@RequestParam String name, @RequestParam String password, @RequestParam String roles) {
        User user = new User(name, password, getRolesFromString(roles));
        if (userService.saveUser(user)) {
            return userService.getUserByName(name);
        } else {
            throw new RuntimeException("error");
        }
    }

    @PostMapping("/change")
    public User changeUser(@RequestParam long id, @RequestParam String name, @RequestParam String password, @RequestParam String roles) {
        User user = new User(id, name, password, getRolesFromString(roles));
        if (userService.changeUser(user)) {
            return user;
        } else {
            throw new RuntimeException("error");
        }
    }

    public Set<Role> getRolesFromString(String rolesName) {
        Set<Role> roles = new HashSet<>();
        for (String role : rolesName.split(",")) {
            roles.add(roleService.getRoleByName(role));
        }

        return roles;
    }


}

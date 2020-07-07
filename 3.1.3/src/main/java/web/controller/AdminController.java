package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("admin")
    public String redirectAdmin() {
        return "redirect:admin/all";
    }

    @GetMapping("admin/all")
    public String showUsers(ModelMap model, @RequestParam(required = false, name = "error") String error) {
        List<User> list = userService.getAllUsers();
        List<Role> listRole = roleService.getAllRoles();
        model.addAttribute("roles", listRole);
        model.addAttribute("users", list);
        model.addAttribute("user", getUser());
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "usersList";
    }

    @PostMapping("admin/delete")
    public String deleteUser(@RequestParam long id, RedirectAttributes redirectAttributes) {
        if (userService.deleteUser(id)) {
            return "redirect:/admin/all";
        }
        redirectAttributes.addAttribute("error", "This User is not exists");
        return "redirect:/admin/all";
    }

    @PostMapping("admin/add")
    public String addUser(@RequestParam String name, @RequestParam String password, @RequestParam String[] roles, RedirectAttributes redirectAttributes) {
        User user = new User(name, password, getRolesFromStrings(roles));
        if (userService.saveUser(user)) {
            return "redirect:/admin/all";
        }
        redirectAttributes.addAttribute("error", "User with the same name already exists");
        return "redirect:/admin/all";
    }

    @PostMapping("admin/change")
    public String changeUser(@RequestParam long id, @RequestParam String name, @RequestParam String password, @RequestParam String[] roles, RedirectAttributes redirectAttributes) {
        User user = new User(id, name, password, getRolesFromStrings(roles));
        if (userService.changeUser(user)) {
            return "redirect:/admin/all";
        }
        redirectAttributes.addAttribute("error", "User with the same name already exists");
        return "redirect:/admin/all";
    }

    public UserDetails getUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Set<Role> getRolesFromStrings(String[] rolesName) {
        Set<Role> roles = new HashSet<>();
        for (String role: rolesName) {
            roles.add(roleService.getRoleByName(role));
        }

        return roles;
    }


}

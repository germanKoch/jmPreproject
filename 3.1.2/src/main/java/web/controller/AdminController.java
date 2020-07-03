package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("admin")
    public String redirectAdmin() {
        return "redirect:admin/all";
    }

    @GetMapping("admin/all")
    public String showUsers(ModelMap model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("users", list);
        model.addAttribute("user",getUser());
        return "usersList";
    }

    @PostMapping("admin/delete")
    public String deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
        return "redirect:/admin/all";
    }

    @PostMapping("admin/add")
    public String addUser(@RequestParam String name, @RequestParam String password) {
        User user = new User(name, password);
        userService.saveUser(user);
        return "redirect:/admin/all";
    }

    @PostMapping("admin/change")
    public String changeUser(@RequestParam long id, @RequestParam String name, @RequestParam String password) {
        User user = new User(id, name, password);
        userService.changeUser(user);
        return "redirect:/admin/all";
    }

    public UserDetails getUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}

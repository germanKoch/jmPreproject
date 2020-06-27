package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String showUsers(ModelMap model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("users", list);
        return "users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
        return "redirect:/all";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam String password) {
        User user = new User(name, password);
        userService.saveUser(user);
        return "redirect:/all";
    }

    @PostMapping("/change")
    public String changeUser(@RequestParam long id, @RequestParam String name, @RequestParam String password) {
        User user = new User(id, name, password);
        userService.changeUser(user);
        return "redirect:/all";
    }


}

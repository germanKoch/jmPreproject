package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.Collections;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String showRegistration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrate(Model model, @RequestParam String name, @RequestParam String password) {
        User newUser = new User(name, password);
        newUser.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        if (!userService.saveUser(newUser)) {
            model.addAttribute("usernameError", "This user is already exists.");
            return "registration";
        }
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

}

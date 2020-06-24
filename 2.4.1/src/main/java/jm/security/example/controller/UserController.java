package jm.security.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class UserController {

    @GetMapping("/user")
    public String getUserPage() {
        return "/user";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "/login";
    }

}

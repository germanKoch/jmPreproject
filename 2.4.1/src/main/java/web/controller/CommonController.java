package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.UserService;

@Controller
public class CommonController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String showForAll() {
        return "all";
    }
}

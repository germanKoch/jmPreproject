package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping("/user")
    public String getUser() {
        return "user";
    }
}
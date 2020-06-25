package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import web.service.UserService;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    
}

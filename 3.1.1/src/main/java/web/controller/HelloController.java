package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController { ;

    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping("/user")
    public String getUser(ModelMap model) {
        UserDetails user  = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        String password = user.getPassword();
        model.addAttribute("name",name);
        model.addAttribute("password",password);
        return "user";
    }
}

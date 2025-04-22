package com.example.login.controller;

import com.example.login.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;


@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String showLoginForm() {
        return "login"; // This will render login.html
    }
    @GetMapping("/index")
    public String showIndexPage() {
        return "index"; // This looks for index.html in src/main/resources/templates
    }



    @GetMapping("/sidebar")
    public String showSidebarPage() {
        return "sidebar"; // This looks for index.html in src/main/resources/templates
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        boolean isValid = loginService.validateUser(username, password);

        if (isValid) {
            return "redirect:/index"; // or "index" if you're using Thymeleaf
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "Login"; // stays on login page
        }
    }
}

package com.example.login.controller;

import com.example.login.Entity.User;
import com.example.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "Register"; // Thymeleaf page: Register.html
    }

}

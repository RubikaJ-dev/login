package com.example.login.controller;

import com.example.login.dto.UserDTO;
import com.example.login.jwtUtils.JwtUtils;
import com.example.login.services.LoginService;
import com.example.login.services.UserServiceImplementation;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // or return a general login page
    }
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserServiceImplementation userServiceImplementation;
//    @PostMapping("/signup")
//    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userInDto)
//    {
//        UserDTO userDto = userServiceImplementation.createUser(userInDto);
//        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
//    }
//    @PostMapping("/login")
//    public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO userDTO) throws BadRequestException {
//        // Validate username and password
//        UserDTO userDto1 = userServiceImplementation.checkLogin(userDTO);
//
//        return new ResponseEntity<>(userDto1, HttpStatus.FOUND);
//    }


    @Autowired
    private LoginService loginService;
    @GetMapping("/user-login")
    public String showUserLoginForm() {
        return "User_login"; // renders user-login.html
    }

    @GetMapping("/admin-login")
    public String showAdminLoginForm() {
        return "Admin_login"; // renders admin-login.html
    }



    // User Dashboard page
    @GetMapping("/user-dashboard")
    public String showUserDashboard() {
        return "usersidebar"; // Thymeleaf page for user dashboard
    }
}


package com.example.login.controller;

import com.example.login.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.Entity.User;
import com.example.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/")
public class RegistrationRestController {


    @Autowired
    private UserService userService;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Handle user registration via JSON POST request
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            System.out.println("Registering user: " + userDTO.getUsername());

            User userEntity = userService.convertDtoToUser(userDTO);
            if (userService.isUserExists(userEntity)) {
                return ResponseEntity.badRequest().body(Map.of("message", "Username already exists"));
            }


            // Use createUser() which handles password encryption + token generation
            UserDTO createdUser = userService.createUser(userDTO);
            createdUser.setPassword(userDTO.getPassword());

            return ResponseEntity.ok(createdUser); // Contains token + user info
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Registration failed: " + e.getMessage()));
        }
    }

}

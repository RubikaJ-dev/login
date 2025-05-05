package com.example.login.controller;


import com.example.login.Entity.User;
import com.example.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/user/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){

        if(this.userService.isUserExists(user)){
            return new ResponseEntity<>("User already exists",HttpStatus.CONFLICT);
        }

       this.userService.addUser(user);
        return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
    }
}

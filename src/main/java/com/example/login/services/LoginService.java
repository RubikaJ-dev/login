package com.example.login.services;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    // Username Password
    public boolean validateUser(String username, String password) {
        return "admin1".equals(username) && "password1234".equals(password);
    }
}

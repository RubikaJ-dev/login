package com.example.login.services;

import com.example.login.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserService userService;  // Assuming this provides a method for fetching users by username

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;  // Use BCryptPasswordEncoder to check passwords

    public boolean validateUser(String username, String password) {
        Optional<User> optionalUser = userService.getUserByUsername(username);

        // Check if the user is present and validate password using BCrypt
        return optionalUser.isPresent() && passwordEncoder.matches(password, optionalUser.get().getPassword());
    }
    public boolean isUserExists(User user) {
        return userService.getUserByUsername(user.getUsername()).isPresent();
    }

    public void addUser(User user) {
        if (isUserExists(user)) {
            throw new RuntimeException("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        userService.addUser(user); // Delegate saving to UserService
    }

}

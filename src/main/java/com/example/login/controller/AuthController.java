package com.example.login.controller;


import com.example.login.Entity.User;
import com.example.login.dto.AuthRequest;
import com.example.login.dto.AuthResponse;
import com.example.login.dto.UserDTO;
import com.example.login.exceptions.BadRequestExceptions;
import com.example.login.jwtUtils.JwtUtils;
import com.example.login.services.LoginService;
import com.example.login.services.UserService;
import com.example.login.services.UserServiceImplementation;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> checkLogin(@RequestBody UserDTO userDto) throws BadRequestExceptions {
        logger.info("Received login request for username: " + userDto.getUsername());

        try {
            UserDTO authenticatedUser = userService.checkLogin(userDto);
            String token = jwtUtil.generateToken(authenticatedUser.getUsername());
            AuthResponse authResponse = new AuthResponse(token);

            logger.info("Generated token for user: " + userDto.getUsername());
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            logger.error("Error during login: " + e.getMessage());
            throw new BadRequestExceptions("Invalid credentials");
        }
    }
}


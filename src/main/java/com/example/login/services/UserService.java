package com.example.login.services;

import com.example.login.dto.UserDTO;
import com.example.login.Entity.User;
import com.example.login.exceptions.BadRequestExceptions;

import java.util.List;
import java.util.Optional;


public interface UserService  {
    UserDTO checkLogin(UserDTO userDto) throws BadRequestExceptions;
    UserDTO createUser(UserDTO users);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long id, User users);

    UserDTO updateUser(Long id, UserDTO userDTO);

    UserDTO deleteUser(Long id);

    void addUser(User user);
    boolean isUserExists(User user);
    Optional<User> getUserByUsername(String username);

    User convertDtoToUser(UserDTO userDTO);

}

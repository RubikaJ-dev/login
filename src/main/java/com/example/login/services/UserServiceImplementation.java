package com.example.login.services;

import com.example.login.dto.UserDTO;
import com.example.login.Entity.User;
import com.example.login.exceptions.BadRequestExceptions;
import com.example.login.jwtUtils.JwtUtils;
import com.example.login.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImplementation implements UserService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    // Authenticate user and generate JWT token
    public UserDTO authenticate(UserDTO userDto) {
        // 1. Authenticate the credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                )
        );

        // 2. Find user from repository
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userDto.getUsername()));

        // 3. Convert user to DTO
        UserDTO response = convertUserToDto(user); // ✅ Correct


        // 4. Generate JWT token
        String token = jwtUtils.generateToken(user.getUsername());
        response.setToken(token);
        response.setExpiresIn(86400L); // 1 day expiration (seconds)

        return response;
    }

    @Override
    public UserDTO checkLogin(UserDTO userDto) throws BadRequestExceptions {


        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
        User user = optionalUser.orElse(null);



        if(user == null){
            throw new BadRequestExceptions("Invalid Username");
        }
        if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            return convertUserToDto(user);

        }else{
            throw new BadRequestExceptions("Invalid Username or Password");
        }

    }

    // Create a new user
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Convert DTO to entity
        User user = convertDtoToUser(userDTO);

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        // Save user
        User savedUser = userRepository.save(user);

        // Convert saved user to DTO
        UserDTO response = convertUserToDto(savedUser);

        // Generate JWT token
        String token = jwtUtils.generateToken(savedUser.getUsername());
        response.setToken(token);
        response.setExpiresIn(jwtUtils.getExpirationTime()); // Dynamic expiration time

        return response;
    }

    // Utility method to convert User entity to UserDTO
    public UserDTO convertUserToDto(User user) {
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
        return userDTO; // ✅ Correct
    }

    // Utility method to convert UserDTO to User entity
    public User convertDtoToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public UserDTO getUserById(Long id) {
        // Fetch user by ID, return null if not found
        return userRepository.findById(id).map(this::convertUserToDto).orElse(null);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        // Fetch all users and convert to DTO list
        List<User> users = userRepository.findAll();
        return modelMapper.map(users, List.class);  // You can fine-tune this
    }

    @Override
    public UserDTO updateUser(Long id, User users) {
        return null;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        // Update user logic
        Optional<User> existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            // Update user fields (simple example, extend as needed)
            existingUser.setUsername(userDTO.getUsername());
            existingUser.setEmail(userDTO.getEmail());
            // You can add more fields for update...
            userRepository.save(existingUser);
            return convertUserToDto(existingUser);
        } else {
            return null;  // Or throw an exception if user is not found
        }
    }

    @Override
    public UserDTO deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
        return user.map(this::convertUserToDto).orElse(null);
    }

    @Override
    public void addUser(User user) {
        // Simple user adding method
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean isUserExists(User user) {
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

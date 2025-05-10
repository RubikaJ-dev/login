package com.example.login.controller;


import com.example.login.Entity.Employee;
import com.example.login.Entity.LeaveApplication;
import com.example.login.Entity.User;
import com.example.login.dto.UserDTO;
import com.example.login.jwtUtils.JwtUtils;
import com.example.login.repository.EmployeeRepository;
import com.example.login.services.AdminService;
import com.example.login.services.EmployeeService;
import com.example.login.services.LeaveService;
import com.example.login.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {

        if (this.userService.isUserExists(user)) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }

        this.userService.addUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @GetMapping("/userdata")
    public ResponseEntity<Map<String, Object>> getUserData(Principal principal) {
        Map<String, Object> response = new HashMap<>();
        try {
            String username = principal.getName();
            Optional<User> optionalUser = employeeService.getEmployeeByUsername(username);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                // Create a manual Employee object if needed
                response.put("username", user.getUsername());
                response.put("id", user.getId());
                response.put("role", user.getRole());
                response.put("userEmail", user.getEmail());

                return ResponseEntity.ok(response);
            } else {
                response.put("error", "User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.clear();
            response.put("error", "Failed to load user data");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitLeaveApplication(@RequestBody LeaveApplication leaveApplication) {
        int empId = leaveApplication.getEmployee_id();
        Employee emp = employeeRepository.findById(empId).orElse(null);

        if (emp != null) {
            leaveApplication.setEmployee(emp);
            leaveService.saveLeaveApplication(leaveApplication);
            return ResponseEntity.status(HttpStatus.CREATED).body("Leave application submitted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Employee ID");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getEmployeeDetailsByUsername(Principal principal) {
        try {
            // Step 1: Get the username from the principal
            String username = principal.getName();
            Optional<User> userOptional = userService.getUserByUsername(username);

            if (userOptional.isPresent()) {
                // Step 2: Get the email from the user
                User user = userOptional.get();
                String email = user.getEmail();

                // Step 3: Fetch employee by email
                Optional<Employee> optionalEmployee = employeeRepository.findByEmail(email);

                if (optionalEmployee.isPresent()) {
                    // Return employee details if found
                    return ResponseEntity.ok(optionalEmployee.get()); // Return the actual Employee object as JSON
                } else {
                    // Return a NOT_FOUND response if no employee is found
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(Map.of("message", "Employee not found with email: " + email));
                }
            } else {
                // Return a NOT_FOUND response if no user is found
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "User not found with username: " + username));
            }
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while fetching employee details"));
        }
    }

}
package com.example.login.controller;


import com.example.login.Entity.Employee;
import com.example.login.Entity.LeaveApplication;
import com.example.login.Entity.User;
import com.example.login.repository.EmployeeRepository;
import com.example.login.services.EmployeeService;
import com.example.login.services.LeaveService;
import com.example.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){

        if(this.userService.isUserExists(user)){
            return new ResponseEntity<>("User already exists",HttpStatus.CONFLICT);
        }

       this.userService.addUser(user);
        return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
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
private EmployeeRepository employeeRepository;

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
}

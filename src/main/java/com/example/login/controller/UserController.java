package com.example.login.controller;

import com.example.login.Entity.LeaveApplication;
import com.example.login.repository.EmployeeRepository;
import com.example.login.services.EmployeeService;
import com.example.login.services.LeaveService;
import com.example.login.services.LeaveServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    LeaveService leaveService;

    // User Dashboard page
    @GetMapping("/user-dashboard")
    public String showUserDashboard() {
        return "employeedashboard"; // Thymeleaf page for user dashboard
    }

    @GetMapping("/profile")
    public String showProfilePage() {
        return "employees";
    }

    @GetMapping("/payslip")
    public String getPayslipPage() {
        return "payslip";  // This directly renders the payslip.html template
    }

@Autowired
private EmployeeRepository employeeRepository;

    @GetMapping("/apply")
    public String showLeaveApplicationForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("employee", userDetails);  // Pass the logged-in user to the model
        return "leaveApplications";  // Thymeleaf template
    }
    // POST request to submit the leave application form
//    @PostMapping("/submit")
//    public String submitLeaveApplication(@ModelAttribute LeaveApplication leaveApplication) {
//        leaveService.saveLeaveApplication(leaveApplication);
//        return "redirect:/leave-confirmation";
//    }
//    @GetMapping("/leave/latest")
//    @ResponseBody  // Tells Spring to return JSON instead of a view
//    public ResponseEntity<LeaveApplication> getLatestLeaveApplication(@AuthenticationPrincipal UserDetails userDetails) {
//        // Get the employee ID or other details from userDetails (assuming you can get employeeId from it)
//        int employeeId = Integer.parseInt(userDetails.getUsername()); // Assuming username is employeeId; adjust accordingly
//
//        // Fetch the latest leave application for the authenticated user
//        LeaveApplication latestLeaveApplication = leaveService.getLatestLeaveApplication(employeeId);
//
//        if (latestLeaveApplication != null) {
//            // Return the leave application as JSON
//            return ResponseEntity.ok(latestLeaveApplication);
//        } else {
//            // If no leave application found, return a 404 Not Found status
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(null);  // You can return a custom message if needed
//        }
//    }
}

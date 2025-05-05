package com.example.login.controller;


import com.example.login.Entity.Employee;
import com.example.login.services.AdminService;
import com.example.login.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RequestMapping("/admin")
@Controller

    public class PaymentController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/pay")
    public String showPaymentPage(Model model) {
        List<Employee> employeeList = adminService.fetchAllEmployee();
        model.addAttribute("employeeList", employeeList);  // Add employeeList to the model
        return "payment";  // Thymeleaf template name
    }

    @GetMapping("/payment-confirmation")
    public String showPaymentConfirmationPage(Model model) {
        // Here, 'orderId' would be passed to the model as needed
        return "paymentConfirmation";
    }


    @PostMapping("/pay-salary/{id}")
    @ResponseBody
    public ResponseEntity<?> createOrder(@PathVariable("id") Long employeeId,
                                         @RequestParam int amount,
                                         @RequestParam String currency,
                                         @RequestParam String receipt) {
        try {
            String orderId = paymentService.createOrder(amount, currency, receipt);

            // Create a JSON response
            Map<String, String> response = new HashMap<>();
            response.put("orderId", orderId);
            response.put("message", "Order created successfully");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error creating payment order: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
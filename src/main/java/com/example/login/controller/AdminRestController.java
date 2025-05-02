package com.example.login.controller;

import com.example.login.Entity.Employee;
import com.example.login.jwtUtils.JwtUtils;
import com.example.login.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class AdminRestController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AdminService adminService;

    @PostMapping("/indexdata")
    public Map<String, String> getIndexData() {
        Map<String, String> response = new HashMap<>();
      try {
          response.put("message", "Welcome to the Admin Dashboard!");
          return response ;
      }catch (Exception e){
          response.clear();
          response.put("Error","Failed to  load Dashboard data");
          return response;
      }
    }

    @GetMapping("/sidebar")
    public Map<String, String> getSidebarData() {
        Map<String, String> response = new HashMap<>();
        response.put("section1", "Dashboard");
        response.put("section2", "Employees");
        response.put("section3", "Salary");
        return response;
    }


    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return adminService.fetchAllEmployee();
    }
}
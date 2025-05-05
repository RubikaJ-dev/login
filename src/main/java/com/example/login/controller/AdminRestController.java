package com.example.login.controller;

import com.example.login.Entity.Employee;
import com.example.login.jwtUtils.JwtUtils;
import com.example.login.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/")
public class AdminRestController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AdminService adminService;

    @GetMapping("/indexdata")
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
    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<?> getEmployeeDetails(@PathVariable int id) {
        Employee employee = adminService.findEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        // Build DTO if you want a clean response, or just return entity tree if safe.
        Map<String, Object> response = new HashMap<>();
        response.put("id", employee.getId());
        response.put("name", employee.getName());
        response.put("designation", employee.getJob_role());
        response.put("department", employee.getDepartment());
        response.put("dateOfBirth", employee.getDate_of_birth());
        response.put("dateOfJoin", employee.getDate_of_join());
        response.put("primaryPhone", employee.getPhone_number());
        response.put("secondaryPhone", employee.getSecondary_phone_number());
        response.put("ctc", employee.getCtc());
        response.put("certificationPath", employee.getCertificationPath());

        // Nested
        response.put("address", employee.getAddress());
        response.put("degree", employee.getDegree());
        response.put("previousEmployment", employee.getPreviousEmployment());

        return ResponseEntity.ok(response);
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
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = adminService.fetchAllEmployee();
        return ResponseEntity.ok(employees);
    }

}
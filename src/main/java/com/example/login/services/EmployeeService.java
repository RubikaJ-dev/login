package com.example.login.services;

import com.example.login.Entity.Employee;
import com.example.login.Entity.User;

import java.util.Optional;

public interface EmployeeService {

    // New method to fetch employee by username
    Optional<User> getEmployeeByUsername(String username);

    Employee approveEmployee(Employee employee);
    Employee viewPayslips();
    Employee submitLeaveRequest();
    Employee getEmployeeByRole();
    Employee getEmployeeByProfile();
    Employee updatePersonalDocuments();
}

package com.example.login.services;

import com.example.login.Entity.Employee;

public interface EmployeeService {
    Employee approveEmployee(Employee employee);
    Employee viewPayslips();
    Employee submitLeaveRequest();
    Employee getEmployeeByRole();
    Employee getEmployeeByProfile();
    Employee updatePersonalDocuments();
}

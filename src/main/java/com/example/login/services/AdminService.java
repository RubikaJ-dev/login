package com.example.login.services;

import com.example.login.Entity.Employee;

import java.util.List;

public interface AdminService {
    List<Employee> fetchAllEmployee();

    Employee findEmployeeById(int id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee,int id);

    void updateEmployee(Employee employee);

    String deleteEmployee(int id);
}
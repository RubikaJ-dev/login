package com.example.login.services;

import com.example.login.Entity.Employee;
import com.example.login.Entity.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<Employee> fetchAllEmployee();

    Employee findEmployeeById(int id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee,int id);

    void updateEmployee(Employee employee);

    Optional<Employee> findByEmail(String email);

    String deleteEmployee(int id);
}
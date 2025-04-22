package com.example.login.services;

import com.example.login.Entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    List<Employee> fetchAllEmployee();

    Employee findEmployeeById(int id);

    Object createEmployee(Object o);

    Object updateEmployee(Object o, int id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee,int id);

    void updateEmployee(Employee employee);

    String deleteEmployee(int id);
}
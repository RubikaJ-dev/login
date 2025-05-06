package com.example.login.services;

import com.example.login.Entity.Employee;
import com.example.login.Entity.User;
import com.example.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService{
    @Autowired
    private UserRepository userRepository;
    // New method to fetch employee by username
    @Override
    public Optional<User> getEmployeeByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public Employee approveEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee viewPayslips() {
        return null;
    }

    @Override
    public Employee submitLeaveRequest() {
        return null;
    }

    @Override
    public Employee getEmployeeByRole() {
        return null;
    }

    @Override
    public Employee getEmployeeByProfile() {
        return null;
    }

    @Override
    public Employee updatePersonalDocuments() {
        return null;
    }
}

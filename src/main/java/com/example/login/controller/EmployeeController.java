package com.example.login.controller;

import com.example.login.services.EmployeeService;
import com.example.login.services.EmployeeServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

}

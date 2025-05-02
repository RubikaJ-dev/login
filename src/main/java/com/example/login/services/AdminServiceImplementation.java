package com.example.login.services;

import com.example.login.Entity.Employee;
import com.example.login.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List fetchAllEmployee() {
        List<Employee> employeeList;
        employeeList = employeeRepository.findAll();
        System.out.println("Employees size: " + employeeList.size());
        return employeeList;

    }

    @Override
    public Employee findEmployeeById(int id) {

        return employeeRepository.findById(id).orElse(null);

    }
    @Override
    public Employee createEmployee(Employee employee) {
        Employee result = employeeRepository.save(employee);
        return result;
    }

    @Override
    public Employee updateEmployee(Employee employee, int id) {
        return null;
    }

    @Override
    public void updateEmployee(Employee employee) {
        // Assuming you are using a repository
        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        if (existingEmployee != null) {
            // Update the employee's properties
            existingEmployee.setName(employee.getName());
            existingEmployee.setAge(employee.getAge());
            existingEmployee.setJob_role(employee.getJob_role());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setDate_of_birth(employee.getDate_of_birth());
            existingEmployee.setPhone_number(employee.getPhone_number());
            existingEmployee.setSecondary_phone_number(employee.getSecondary_phone_number());
            existingEmployee.setCtc(employee.getCtc());

            // Update address, degree, previous employment, etc.
            existingEmployee.getAddress().setStreet(employee.getAddress().getStreet());
            existingEmployee.getAddress().setCity(employee.getAddress().getCity());
            existingEmployee.getAddress().setState(employee.getAddress().getState());
            existingEmployee.getDegree().setDegree_name(employee.getDegree().getDegree_name());
            existingEmployee.getPreviousEmployment().setCompany_name(employee.getPreviousEmployment().getCompany_name());

            // Save the updated employee
            employeeRepository.save(existingEmployee);
        }
    }


    @Override
    public String deleteEmployee(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Employee not found with id " + id);
        }
        return "";
    }
}
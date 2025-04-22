package com.example.login.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.annotation.Id;
import org.springframework.ui.Model;  // Correct import for Spring MVC Model

import com.example.login.Entity.Address;
import com.example.login.Entity.Degree;
import com.example.login.Entity.Employee;
import com.example.login.Entity.PreviousEmployment;
import com.example.login.services.EmployeeService;
import jakarta.persistence.criteria.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static java.lang.System.*;

@Controller

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public String showEmployeePage(Model model) {
        List<Employee> employees = employeeService.fetchAllEmployee();
        model.addAttribute("employees", employees);
        return "employee";
    }
    @RestController
    @RequestMapping("/api")
    public class EmployeeRestController {

        @Autowired
        private EmployeeService employeeService;

        @GetMapping("/employees")
        public List<Employee> getAllEmployees() {
            return employeeService.fetchAllEmployee();
        }
    }
    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("age") String age,
            @RequestParam("designation") String designation,
            @RequestParam("department") String department,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("primaryPhone") String primaryPhone,
            @RequestParam("secondaryPhone") String secondaryPhone,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("degree") String degree,
            @RequestParam("previousEmployment") String previousEmployment,
            @RequestParam("ctc") String ctc,
            @RequestParam("email") String email){

        System.out.println("NAme:"+name);
        System.out.println("email:"+email);
        System.out.println("Age:"+age);
        LocalDate dob = null;
        try {
            if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                dob = LocalDate.parse(dateOfBirth, formatter);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date format. Please use dd-MM-yyyy.");
        }

        if (dob == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Date of Birth cannot be empty.");
        }



        // Create the employee object and populate it
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        employee.setJob_role(designation);
        employee.setEmail(email); // Set email value
        employee.setDate_of_join(Date.valueOf(LocalDate.now())); // Set current date for join
        java.sql.Date sqlDate = java.sql.Date.valueOf(dob);

        employee.setDate_of_birth(java.sql.Date.valueOf(dob));
        employee.setPhone_number(primaryPhone);
        employee.setSecondary_phone_number(secondaryPhone);

        Degree degree1 = new Degree();
        degree1.setDegree_name(degree);
        degree1.setEmployee(employee);

        PreviousEmployment previousEmployment1 = new PreviousEmployment();
        previousEmployment1.setCompany_name(previousEmployment);
        previousEmployment1.setEmployee(employee);
        employee.setCtc(ctc);

        Address address1 =new Address();
        address1.setStreet(address);
        address1.setCity(city);
        address1.setState(state);
        address1.setEmployee(employee);

        // Save the employee data
        employeeService.createEmployee(employee);

        // Handle file upload
        if (!file.isEmpty()) {
            try {
                // Define the upload directory
                String uploadDir = "uploads/";

                // Create the directory if it doesn't exist
                java.nio.file.Path path = Paths.get(uploadDir + file.getOriginalFilename());
                if (!Files.exists(path.getParent())) {
                    Files.createDirectories(path.getParent());
                }

                // Write the file to the defined path
                Files.write(path, file.getBytes());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
            }
        }

        return ResponseEntity.ok("Employee added successfully");
    }


    @GetMapping("/add")
    public String showAddPage() {
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable int id, Model model) {
        Employee employee = employeeService.findEmployeeById(id); // Fetch the employee by ID
        model.addAttribute("employee", employee); // Add employee object to the model
        return "edit";
    }


    @PostMapping("/updateEmployee/{id}")
    public String updateEmployee(@ModelAttribute Employee employee) {
        Employee existingEmployee = employeeService.findEmployeeById(employee.getId());


        if (existingEmployee != null) {
            // Update the employee details
            existingEmployee.setName(employee.getName());
            existingEmployee.setAge(employee.getAge());
            existingEmployee.setJob_role(employee.getJob_role());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setDate_of_birth(employee.getDate_of_birth());
            existingEmployee.setPhone_number(employee.getPhone_number());
            existingEmployee.setSecondary_phone_number(employee.getSecondary_phone_number());
            existingEmployee.setCtc(employee.getCtc());
            existingEmployee.getAddress().setStreet(employee.getAddress().getStreet());
            existingEmployee.getAddress().setCity(employee.getAddress().getCity());
            existingEmployee.getAddress().setState(employee.getAddress().getState());
            existingEmployee.getDegree().setDegree_name(employee.getDegree().getDegree_name());
            existingEmployee.getPreviousEmployment().setCompany_name(employee.getPreviousEmployment().getCompany_name());

            // Save the updated employee
            employeeService.createEmployee(existingEmployee); // You might want to create an update method in your service

            return "redirect:/employee"; // Redirect to employee list after update
        }

        return "redirect:/employee"; // Handle invalid employee
    }


    @GetMapping("/view/{id}")
    public String showViewPage(@PathVariable int id, Model model){
        Employee employee = employeeService.findEmployeeById(id);

        if (employee == null) {
            return "redirect:/employee"; // handle invalid ID gracefully
        }

        Address address = employeeService.findEmployeeById(employee.getId()).getAddress();
        Degree degree = employeeService.findEmployeeById(employee.getId()).getDegree();
        PreviousEmployment prevEmp = employeeService.findEmployeeById(employee.getId()).getPreviousEmployment();

        model.addAttribute("employee", employee);
        model.addAttribute("address", address);  // ⚠️ Make sure this is not null
        model.addAttribute("degree", degree);
        model.addAttribute("previousEmployment", prevEmp);
        return "view"; // this will load view.html with populated data
        }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

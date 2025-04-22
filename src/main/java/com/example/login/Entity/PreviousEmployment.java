package com.example.login.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name="previousemployment")
public class PreviousEmployment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String company_name;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private String job_title;
    private long years_of_experience;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public long getYears_of_experience() {
        return years_of_experience;
    }

    public void setYears_of_experience(long years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id",  referencedColumnName = "id") // Foreign key in address table
    @JsonBackReference
    private Employee employee;
}
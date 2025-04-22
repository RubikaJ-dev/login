package com.example.login.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.Year;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="degree")
public class Degree {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String degree_name;
    private String institution;
    private Year year_completed;

    public String getDegree_name() {
        return degree_name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setDegree_name(String degree_name) {
        this.degree_name = degree_name;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Year getYear_completed() {
        return year_completed;
    }

    public void setYear_completed(Year year_completed) {
        this.year_completed = year_completed;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id",  referencedColumnName = "id") // Foreign key in address table
    @JsonBackReference
    private Employee employee;
}

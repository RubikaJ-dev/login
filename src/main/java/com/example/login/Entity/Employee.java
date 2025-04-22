package com.example.login.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="users")

public class Employee {
    @Id
    @GeneratedValue(strategy = IDENTITY)

    private int id;
    private String name;
    private String age;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date_of_join;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date_of_birth;

    private String job_role;
    private String phone_number;
    private String secondary_phone_number;
    private String ctc;
    private String email;
    private String department;
   private String certificationPath;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Address address;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCertificationPath() {
        return certificationPath;
    }

    public void setCertificationPath(String certificationPath) {
        this.certificationPath = certificationPath;
    }

    public Address getAddress() {
        if (address == null) {
            address = new Address();
            address.setEmployee(this); // optional but useful
        }
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Degree getDegree() {
        if (degree == null) {
            degree = new Degree();
            degree.setEmployee(this); // optional but useful
        }
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public PreviousEmployment getPreviousEmployment() {
        if (previousEmployment == null) {
            previousEmployment = new PreviousEmployment();
            previousEmployment.setEmployee(this); // optional but useful
        }
        return previousEmployment;
    }

    public void setPreviousEmployment(PreviousEmployment previousEmployment) {
        this.previousEmployment = previousEmployment;
    }

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Degree degree;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private PreviousEmployment previousEmployment;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getDate_of_join() {
        return date_of_join;
    }

    public void setDate_of_join(Date date_of_join) {
        this.date_of_join = date_of_join;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getJob_role() {
        return job_role;
    }

    public void setJob_role(String job_role) {
        this.job_role = job_role;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSecondary_phone_number() {
        return secondary_phone_number;
    }

    public void setSecondary_phone_number(String secondary_phone_number) {
        this.secondary_phone_number = secondary_phone_number;
    }

    public String getCtc() {
        return ctc;
    }

    public void setCtc(String ctc) {
        this.ctc = ctc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}


package com.example.login.repository;

import com.example.login.Entity.LeaveApplication;
import org.springframework.context.annotation.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<LeaveApplication, String> {

}
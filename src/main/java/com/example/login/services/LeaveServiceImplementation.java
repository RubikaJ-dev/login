package com.example.login.services;

import com.example.login.Entity.LeaveApplication;
import com.example.login.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveServiceImplementation implements LeaveService {
@Autowired
private LeaveRepository leaveRepository;

    @Override
    public void saveLeaveApplication(LeaveApplication leaveApplication) {
     leaveRepository.save(leaveApplication);
    }

    @Override
    public LeaveApplication getLeaveApplicationById(String employeeId) {
        return leaveRepository.findById(employeeId).orElse(null);
    }

//    @Override
//    public LeaveApplication getLatestLeaveApplication(int employeeId) {
//        LeaveApplication leave1 = new LeaveApplication();
//        leave1.setEmployeeId(Integer.parseInt("123"));
//        leave1.setEmployeeName("John Doe");
//        leave1.setLeaveType("Sick Leave");
//        leave1.setStartDate("2025-04-01");
//        leave1.setEndDate("2025-04-05");
//        leave1.setLeaveReason("Personal illness");
//
//        LeaveApplication leave2 = new LeaveApplication();
//        leave2.setEmployeeId(Integer.parseInt("EMP123"));
//        leave2.setEmployeeName("John Doe");
//        leave2.setLeaveType("Vacation Leave");
//        leave2.setStartDate("2025-05-01");
//        leave2.setEndDate("2025-05-07");
//        leave2.setLeaveReason("Family trip");
//
//        // Simulating all leave applications (replace with real data retrieval logic)
//        List<LeaveApplication> allLeaveApplications = new ArrayList<>();
//        allLeaveApplications.add(leave1);
//        allLeaveApplications.add(leave2);
//
//        // Logic to find the most recent leave application for the given employee
//        LeaveApplication latestLeave = null;
//        for (LeaveApplication leave : allLeaveApplications) {
//            if (leave.getEmployeeId()) {
//                if (latestLeave == null || leave.getStartDate().compareTo(latestLeave.getStartDate()) > 0) {
//                    latestLeave = leave;
//                }
//            }
//        }
//
//        return latestLeave;
    }


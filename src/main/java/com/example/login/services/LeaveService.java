package com.example.login.services;

import com.example.login.Entity.LeaveApplication;

public interface LeaveService {
    void saveLeaveApplication(LeaveApplication leaveApplication);

    LeaveApplication getLeaveApplicationById(String employeeId);

//    LeaveApplication getLatestLeaveApplication(int employeeId);
}

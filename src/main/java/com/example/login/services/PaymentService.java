package com.example.login.services;


import com.example.login.Entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface PaymentService {
    String createOrder(int amount, String currency, String receipt) throws Exception;

    @Service
    class UserService {

        private  static final ArrayList<User> users = new ArrayList<>();

        public void addUser(User user){
            UserService.users.add(user);
        }

        public boolean isUserExists(User user){
            return UserService.users.stream().anyMatch(obj->obj.getEmail().equalsIgnoreCase(user.getEmail()));
        }
    }
}

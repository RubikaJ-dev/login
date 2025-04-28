package com.example.login.services;


public interface PaymentService {
    String createOrder(int amount, String currency, String receipt) throws Exception;
}

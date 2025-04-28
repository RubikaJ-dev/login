package com.example.login.services;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class Razorpay implements PaymentService{


    @Override
    public String createOrder(int amount, String currency, String receipt) throws Exception {
        RazorpayClient client = new RazorpayClient("rzp_test_xpt5Aiy2c4N9Jx", "Cgaj9uNfDva7bdZN2I5Eapsz");

        JSONObject options = new JSONObject();
        options.put("amount", amount * 100); // Amount in paise
        options.put("currency", currency);
        options.put("receipt", receipt);
        options.put("payment_capture", 1); // Auto capture

        Order order = client.orders.create(options);
        return order.get("id");
    }

}

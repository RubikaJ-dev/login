package com.example.login.services;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class RazorpayManualService {

    public String createOrderManually(int amount, String currency, String receipt) {
        String url = "https://api.razorpay.com/v1/orders";

        // Create request body
        JSONObject request = new JSONObject();
        request.put("amount", amount * 100); // in paise
        request.put("currency", currency);
        request.put("receipt", receipt);
        request.put("payment_capture", 1);

        // Basic Auth header (KeyId:KeySecret)
        String keyId = "rzp_test_xpt5Aiy2c4N9Jx";
        String keySecret = "Cgaj9uNfDva7bdZN2I5Eapsz";
        String auth = keyId + ":" + keySecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + encodedAuth);

        HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

        // Call Razorpay API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody(); // This is the raw JSON order response
    }
}

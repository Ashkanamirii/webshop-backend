package com.nackademin.webshopbackend.client.payment;

import com.nackademin.webshopbackend.client.emailClient.ClientEmailDTO;
import com.nackademin.webshopbackend.client.emailClient.EmailContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.nackademin.webshopbackend.constant.EmailConstant.BASEURL;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-06
 * Time:  15:11
 * Project: webshop-backend
 * Copyright: MIT
 */

@Component
public class PaymentClient {

    private RestTemplate restTemplate;
    private final String url = "https://hakimlivs-payment-gateway.herokuapp.com/payment";

    public PaymentClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendPayment(PaymentDto paymentDto) {

        final ResponseEntity<Void> response = restTemplate.
                postForEntity(url, paymentDto, Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getStatusCode().toString();
        }

        throw new RuntimeException("Could not handle payment");
    }
}

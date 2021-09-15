package com.nackademin.webshopbackend.client.payment;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-06
 * Time:  15:11
 * Project: webshop-backend
 * Copyright: MIT
 */

@Component
@Data
public class PaymentClient {

	private RestTemplate restTemplate;
	private String url = "https://hakimlivs-payment-gateway.herokuapp.com/payment";

	public PaymentClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String sendPayment(PaymentDto paymentDto) {

		final ResponseEntity<Void> response = restTemplate.
				postForEntity(url, paymentDto, Void.class);

		if (response.getStatusCode().is2xxSuccessful()) {
			return response.getStatusCode().toString();
		}
		return "Payment transaction went wrong";
	}
}
